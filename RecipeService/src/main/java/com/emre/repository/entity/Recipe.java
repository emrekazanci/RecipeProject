package com.emre.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Recipe extends Base implements Serializable{
    @Id
    private String recipeId;
    private String foodName;
    private String type;
    private String preparationTime;
    private String cookingTime;
    private String stepOfRecipe;
    private String image;
    @NotNull(message = "Lütfen bir kategori giriniz.")
    private List<String> category = new ArrayList<>();
    private List<String> comment = new ArrayList<>(); //commentId
    private List<Ingredient> ingredients = new ArrayList<>();
    private List<String> point = new ArrayList<>(); //pointId
    private NutritionalValue nutritionalValue;

}
