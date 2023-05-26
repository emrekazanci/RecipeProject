package com.emre.dto.request;

import com.emre.repository.entity.Ingredient;
import com.emre.repository.entity.NutritionalValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaveRecipeRequestDto {
    @NotBlank(message = "Lütfen bir tarif adı giriniz.")
    private String foodName;
    private String type;
    private String preparationTime;
    private String cookingTime;
    private String stepOfRecipe;
    private String image;
    private List<String> category = new ArrayList<>();
    private List<Ingredient> ingredients = new ArrayList<>();
    private NutritionalValue nutritionalValue;

}
