package com.emre.service;

import com.emre.dto.request.GetIngredientsName;
import com.emre.dto.request.SaveRecipeRequestDto;
import com.emre.dto.request.UpdateRecipeRequestDto;
import com.emre.dto.response.GetRecipeIdAndCategoryIdResponseDto;
import com.emre.dto.response.GetUserForFavCategory;
import com.emre.exception.ErrorType;
import com.emre.exception.RecipeManagerException;
import com.emre.manager.ICommentManager;
import com.emre.manager.IPointManager;
import com.emre.manager.IUserManager;
import com.emre.mapper.IRecipeMapper;
import com.emre.rabbitmq.model.FavCategoriesSendMailModel;
import com.emre.rabbitmq.producer.FavCategoriesMailProducer;
import com.emre.repository.IRecipeRepository;
import com.emre.repository.entity.ERole;
import com.emre.repository.entity.Recipe;
import com.emre.utility.JwtTokenProvider;
import com.emre.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RecipeService extends ServiceManager<Recipe, String> {
    private final IRecipeRepository recipeRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final ICommentManager commentManager;
    private final IPointManager pointManager;
    private final IUserManager userManager;
    private final CategoryService categoryService;
    private final FavCategoriesMailProducer favCategoriesMailProducer;

    public RecipeService(IRecipeRepository recipeRepository,
                         JwtTokenProvider jwtTokenProvider,
                         ICommentManager commentManager,
                         IPointManager pointManager,
                         IUserManager userManager,
                         CategoryService categoryService,
                         FavCategoriesMailProducer favCategoriesMailProducer) {
        super(recipeRepository);
        this.recipeRepository = recipeRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.commentManager = commentManager;
        this.pointManager = pointManager;
        this.userManager = userManager;
        this.categoryService = categoryService;
        this.favCategoriesMailProducer = favCategoriesMailProducer;
    }

    public Recipe saveRecipe(String token, SaveRecipeRequestDto dto) {
        Optional<String> role = jwtTokenProvider.getRoleFromToken(token);
        Recipe recipe = IRecipeMapper.INSTANCE.saveFromSaveRecipeDtoToRecipe(dto);
        if (role.get().equals(ERole.ADMIN.toString())) {
            if (dto.getCategory().isEmpty()) {
                throw new RuntimeException("Lütfen kategori ekleyin");
            }
            dto.getCategory().forEach(x -> {
                if (!categoryService.existsByCategoryId(x)) {
                    throw new RecipeManagerException(ErrorType.CATEGORY_NOT_FOUND);
                }
            });
            save(recipe);
        } else {
            throw new RecipeManagerException(ErrorType.USER_NOT_ADD_DELETE_UPDATE);
        }
        Set<GetUserForFavCategory> getUserForFavCategories = userManager.getUserWithFavCategory(recipe.getCategory()).getBody();
        getUserForFavCategories.forEach(x -> {
            favCategoriesMailProducer.sendMailForFavCategory(FavCategoriesSendMailModel.builder()
                    .recipeName(dto.getFoodName())
                    .username(x.getUsername())
                    .email(x.getEmail())
                    .build());
        });
        return recipe;
    }

    public Recipe updateRecipe(String token, UpdateRecipeRequestDto dto) {
        Optional<String> role = jwtTokenProvider.getRoleFromToken(token);
        Optional<Recipe> recipe = recipeRepository.findById(dto.getRecipeId());
        if (role.get().equals(ERole.ADMIN.toString())) {
            if (recipe.isEmpty()) throw new RecipeManagerException(ErrorType.RECIPE_NOT_FOUND);
            update(IRecipeMapper.INSTANCE.updateFromUpdateRecipeDtoToRecipe(dto, recipe.get()));
        } else {
            throw new RecipeManagerException(ErrorType.USER_NOT_ADD_DELETE_UPDATE);
        }
        return recipe.get();
    }

    public Boolean deleteRecipe(String token, String recipeId) {
        Optional<String> role = jwtTokenProvider.getRoleFromToken(token);
        Optional<Recipe> recipe = recipeRepository.findById(recipeId);
        if (role.get().equals(ERole.ADMIN.toString()) && recipe.isPresent()) {
            deleteById(recipeId);
            commentManager.deleteCommentForDeletedRecipe(recipeId);
            pointManager.deletePointForDeletedRecipe(recipeId);
            return true;
        } else {
            throw new RecipeManagerException(ErrorType.USER_NOT_ADD_DELETE_UPDATE);
        }
    }

    public Boolean sendCommentId(String commentId, String recipeId) {
        Optional<Recipe> recipe = recipeRepository.findById(recipeId);
        if (recipe.isEmpty()) throw new RecipeManagerException(ErrorType.RECIPE_NOT_FOUND);
        recipe.get().getComment().add(commentId);
        update(recipe.get());
        return true;
    }

    public String getRecipeIdForIsExistRecipe(String recipeId) {
        Optional<Recipe> recipe = recipeRepository.findById(recipeId);
        if (recipe.isEmpty()) throw new RecipeManagerException(ErrorType.RECIPE_NOT_FOUND);
        return recipe.get().getRecipeId();
    }

    public Boolean deleteCommentInRecipeForDeletedComment(String recipeId, String commentId) {
        Optional<Recipe> recipe = recipeRepository.findById(recipeId);
        if (recipe.isEmpty()) throw new RecipeManagerException(ErrorType.RECIPE_NOT_FOUND);
        recipe.get().getComment().remove(commentId);
        update(recipe.get());
        return true;
    }

    public Boolean sendPointId(String pointId, String recipeId) {
        Optional<Recipe> recipe = recipeRepository.findById(recipeId);
        if (recipe.isEmpty()) throw new RecipeManagerException(ErrorType.RECIPE_NOT_FOUND);
        recipe.get().getPoint().add(pointId);
        update(recipe.get());
        return true;
    }

    public Boolean deletePointInRecipeForDeletedPoint(String pointId, String recipeId) {
        Optional<Recipe> recipe = recipeRepository.findById(recipeId);
        if (recipe.isEmpty()) throw new RecipeManagerException(ErrorType.RECIPE_NOT_FOUND);
        recipe.get().getPoint().remove(pointId);
        update(recipe.get());
        return true;
    }

    public GetRecipeIdAndCategoryIdResponseDto getRecipeIdAndCategoryId(String recipeId) {
        Optional<Recipe> recipe = recipeRepository.findById(recipeId);
        if (recipe.isEmpty()) throw new RecipeManagerException(ErrorType.RECIPE_NOT_FOUND);
        List<String> categoryIds = recipe.get().getCategory();
        return GetRecipeIdAndCategoryIdResponseDto.builder().recipeId(recipeId).categoryId(categoryIds).build();
    }

    //Kategorilerine göre
    public List<Recipe> searchRecipeWithCategory(List<String> categoryIds) {
        List<Recipe> recipeList = recipeRepository.findAll();
        Set<Recipe> newList = new HashSet<>();
        if (categoryIds.isEmpty()) {
            return recipeList;
        }
        categoryIds.forEach(categoryId -> {
            recipeList.forEach(recipe -> {
                if (!recipe.getCategory().contains(categoryId)) {
                    newList.add(recipe);
                }
            });
        });
        recipeList.removeAll(newList);
        return recipeList;
    }

    //Yemek isimlerine göre
    public List<Recipe> searchRecipeWithFoodName(String foodName) {
        List<Recipe> recipeList = recipeRepository.findByFoodNameContainsIgnoreCase(foodName);
        if (foodName.isEmpty() || recipeList.isEmpty()) {
            recipeList = recipeRepository.findAll();
        }
        return recipeList;
    }

    //Malzeme içeriklerine göre
    public List<Recipe> searchRecipeWithIngredientsName(GetIngredientsName dto) {
        List<Recipe> recipeList = recipeRepository.findAll();
        Set<Recipe> addList = new HashSet<>();

        if (dto.getIngredientsName().isEmpty()) {
            return recipeList;
        }
        dto.getIngredientsName().forEach(ingredientName -> {
            recipeList.forEach(recipe -> {
                recipe.getIngredients().forEach(ingredient -> {
                    if (ingredient.getIngredientName().equals(ingredientName)) {
                        addList.add(recipe);
                    }
                });
            });
        });
        List<Recipe> finalList = new ArrayList<>(addList);
        return finalList;
    }

    //Kaloriye göre sıralama
    public List<Recipe> orderByRecipeWithCalories() {
        List<Recipe> recipes = recipeRepository.findAll();
        Comparator<Recipe> recipeComparator = Comparator.comparing(recipe -> recipe.getNutritionalValue().getCalorie());
        Collections.sort(recipes, recipeComparator);
        return recipes;
    }

}
