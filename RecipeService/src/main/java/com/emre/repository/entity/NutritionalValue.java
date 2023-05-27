package com.emre.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NutritionalValue implements Serializable {
    private String name;
    private Double calorie;
    private Double protein;
    private Double carbohydrate;
    private Double fat;

}
