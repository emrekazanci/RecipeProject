package com.emre.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class User extends Base {
    @Id
    private String userId;
    private Long authId;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String username;
    private String avatar;
    private String street;
    private String neighborhood;
    private String district;
    private String province;
    private String country;
    private Integer buildingNumber;
    private Integer apartmentNumber;
    private Integer zipCode;

    private List<String> favoriteRecipe = new ArrayList<>(); //Favori RecipeId
    private List<String> favoriteCategory = new ArrayList<>(); //Favori Recipe'nin Category'sinin Id'si

}
