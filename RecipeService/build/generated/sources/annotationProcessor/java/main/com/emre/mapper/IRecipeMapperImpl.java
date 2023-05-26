package com.emre.mapper;

import com.emre.dto.request.SaveRecipeRequestDto;
import com.emre.dto.request.UpdateRecipeRequestDto;
import com.emre.repository.entity.Ingredient;
import com.emre.repository.entity.Recipe;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-26T02:24:43+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 17.0.6 (Amazon.com Inc.)"
)
@Component
public class IRecipeMapperImpl implements IRecipeMapper {

    @Override
    public Recipe saveFromSaveRecipeDtoToRecipe(SaveRecipeRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Recipe.RecipeBuilder<?, ?> recipe = Recipe.builder();

        recipe.foodName( dto.getFoodName() );
        recipe.type( dto.getType() );
        recipe.preparationTime( dto.getPreparationTime() );
        recipe.cookingTime( dto.getCookingTime() );
        recipe.stepOfRecipe( dto.getStepOfRecipe() );
        recipe.image( dto.getImage() );
        List<String> list = dto.getCategory();
        if ( list != null ) {
            recipe.category( new ArrayList<String>( list ) );
        }
        List<Ingredient> list1 = dto.getIngredients();
        if ( list1 != null ) {
            recipe.ingredients( new ArrayList<Ingredient>( list1 ) );
        }
        recipe.nutritionalValue( dto.getNutritionalValue() );

        return recipe.build();
    }

    @Override
    public Recipe updateFromUpdateRecipeDtoToRecipe(UpdateRecipeRequestDto dto, Recipe recipe) {
        if ( dto == null ) {
            return recipe;
        }

        if ( dto.getRecipeId() != null ) {
            recipe.setRecipeId( dto.getRecipeId() );
        }
        if ( dto.getFoodName() != null ) {
            recipe.setFoodName( dto.getFoodName() );
        }
        if ( dto.getType() != null ) {
            recipe.setType( dto.getType() );
        }
        if ( dto.getPreparationTime() != null ) {
            recipe.setPreparationTime( dto.getPreparationTime() );
        }
        if ( dto.getCookingTime() != null ) {
            recipe.setCookingTime( dto.getCookingTime() );
        }
        if ( dto.getStepOfRecipe() != null ) {
            recipe.setStepOfRecipe( dto.getStepOfRecipe() );
        }
        if ( dto.getImage() != null ) {
            recipe.setImage( dto.getImage() );
        }
        if ( recipe.getIngredients() != null ) {
            List<Ingredient> list = dto.getIngredients();
            if ( list != null ) {
                recipe.getIngredients().clear();
                recipe.getIngredients().addAll( list );
            }
        }
        else {
            List<Ingredient> list = dto.getIngredients();
            if ( list != null ) {
                recipe.setIngredients( new ArrayList<Ingredient>( list ) );
            }
        }
        if ( dto.getNutritionalValue() != null ) {
            recipe.setNutritionalValue( dto.getNutritionalValue() );
        }

        return recipe;
    }
}
