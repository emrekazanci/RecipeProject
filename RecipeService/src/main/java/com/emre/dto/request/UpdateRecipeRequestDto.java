package com.emre.dto.request;

import com.emre.repository.entity.Ingredient;
import com.emre.repository.entity.NutritionalValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRecipeRequestDto {
    private String recipeId;
    private String foodName;
    private String type;
    private String preparationTime;
    private String cookingTime;
    private String stepOfRecipe;
    private String image;
    private List<Ingredient> ingredients;
    private NutritionalValue nutritionalValue;
}
