package com.emre.manager;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(url = "http://localhost:8070/api/v1/point",name = "recipe-point")
public interface IPointManager {
    @PostMapping("/delete-point-for-deleted-recipe/{recipeId}")
    ResponseEntity<Boolean> deletePointForDeletedRecipe(@PathVariable String recipeId);
}
