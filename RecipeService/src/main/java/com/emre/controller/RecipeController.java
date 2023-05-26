package com.emre.controller;

import com.emre.dto.request.GetIngredientsName;
import com.emre.dto.request.SaveRecipeRequestDto;
import com.emre.dto.request.UpdateRecipeRequestDto;
import com.emre.dto.response.GetRecipeIdAndCategoryIdResponseDto;
import com.emre.repository.entity.Recipe;
import com.emre.service.RecipeService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.emre.constant.EndPoints.*;

@RestController
@RequestMapping(RECIPE)
@RequiredArgsConstructor
public class RecipeController {
    private final RecipeService recipeService;

    @PostMapping(RECIPE_SAVE + "/{token}")
    public ResponseEntity<Recipe> saveRecipe(@PathVariable String token, @RequestBody @Valid SaveRecipeRequestDto dto) {
        return ResponseEntity.ok(recipeService.saveRecipe(token, dto));
    }

    @PutMapping(UPDATE + "/{token}")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable String token, @RequestBody UpdateRecipeRequestDto dto) {
        return ResponseEntity.ok(recipeService.updateRecipe(token, dto));
    }

    @DeleteMapping(RECIPE_DELETE + "/{token}/{recipeId}")
    public ResponseEntity<Boolean> delete(@PathVariable String token, @PathVariable String recipeId) {
        return ResponseEntity.ok(recipeService.deleteRecipe(token, recipeId));
    }

    @PostMapping(SEARCH_RECIPE_WITH_CATEGORY)
    public ResponseEntity<List<Recipe>> getRecipeWithCategory(@RequestBody List<String> categoryIds) {
        return ResponseEntity.ok(recipeService.searchRecipeWithCategory(categoryIds));
    }

    @GetMapping(SEARCH_RECIPE_WITH_FOOD_NAME + "/{foodName}")
    public ResponseEntity<List<Recipe>> searchRecipeWithFoodName(@PathVariable String foodName) {
        return ResponseEntity.ok(recipeService.searchRecipeWithFoodName(foodName));
    }

    @PostMapping(SEARCH_RECIPE_WITH_INGREDIENTS_NAME)
    public ResponseEntity<List<Recipe>> searchRecipeWithIngredientsName(@RequestBody GetIngredientsName dto) {
        return ResponseEntity.ok(recipeService.searchRecipeWithIngredientsName(dto));
    }

    @GetMapping(ORDER_BY_RECIPE_WITH_CALORIES)
    public ResponseEntity<List<Recipe>> orderByRecipeWithCalories() {
        return ResponseEntity.ok(recipeService.orderByRecipeWithCalories());
    }

    @GetMapping(FIND_ALL)
    public ResponseEntity<List<Recipe>> findAll() {
        return ResponseEntity.ok(recipeService.findAll());
    }

    //=================================================

    @PostMapping("/send-comment-id/{commentId}/{recipeId}")
    @Hidden
    ResponseEntity<Boolean> sendCommentId(@PathVariable String commentId, @PathVariable String recipeId) {
        return ResponseEntity.ok(recipeService.sendCommentId(commentId, recipeId));
    }

    @GetMapping("/get-recipe-id/{recipeId}")
    @Hidden
    ResponseEntity<String> getRecipeIdForIsExistRecipe(@PathVariable String recipeId) {
        return ResponseEntity.ok(recipeService.getRecipeIdForIsExistRecipe(recipeId));
    }

    @PostMapping("/delete-comment-in-recipe-for-deleted-comment/{recipeId}/{commentId}")
    @Hidden
    ResponseEntity<Boolean> deleteCommentInRecipeForDeletedComment(@PathVariable String recipeId, @PathVariable String commentId) {
        return ResponseEntity.ok(recipeService.deleteCommentInRecipeForDeletedComment(recipeId, commentId));
    }

    @PostMapping("/send-point-id/{pointId}/{recipeId}")
    @Hidden
    public ResponseEntity<Boolean> sendPointId(@PathVariable String pointId, @PathVariable String recipeId) {
        return ResponseEntity.ok(recipeService.sendPointId(pointId, recipeId));
    }

    @PostMapping("/delete-point-in-recipe-for-deleted-point/{pointId}/{recipeId}")
    @Hidden
    public ResponseEntity<Boolean> deletePointInRecipeForDeletedPoint(@PathVariable String pointId, @PathVariable String recipeId) {
        return ResponseEntity.ok(recipeService.deletePointInRecipeForDeletedPoint(pointId, recipeId));
    }

    @GetMapping("/get-recipe-id-and-category-id-in-recipe/{recipeId}")
    @Hidden
    ResponseEntity<GetRecipeIdAndCategoryIdResponseDto> getRecipeIdAndCategoryId(@PathVariable String recipeId) {
        return ResponseEntity.ok(recipeService.getRecipeIdAndCategoryId(recipeId));
    }
}
