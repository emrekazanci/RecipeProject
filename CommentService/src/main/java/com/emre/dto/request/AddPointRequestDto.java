package com.emre.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddPointRequestDto {
    @Min(0)
    @Max(5)
    @NotNull(message = "Lütfen bir puan giriniz.")
    private Double point;
    @NotBlank(message = "Lütfen bir RecipeId giriniz, boş bırakmayınız.")
    private String recipeId;
}
