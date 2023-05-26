package com.emre.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NutritionalValue {
    private String name;
    private Double calorie;
    private Double protein;
    private Double carbohydrate;
    private Double fat;

}
