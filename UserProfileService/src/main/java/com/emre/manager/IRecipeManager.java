package com.emre.manager;

import com.emre.dto.response.GetRecipeIdAndCategoryIdResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://localhost:8080/api/v1/recipe", name = "userprofile-recipe")
public interface IRecipeManager {
    @GetMapping("/get-recipe-id-and-category-id-in-recipe/{recipeId}")
    ResponseEntity<GetRecipeIdAndCategoryIdResponseDto> getRecipeIdAndCategoryId(@PathVariable String recipeId);
}
