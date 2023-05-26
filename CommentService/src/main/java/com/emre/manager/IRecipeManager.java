package com.emre.manager;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(url = "http://localhost:8080/api/v1/recipe", name = "comment-recipe")
public interface IRecipeManager {
    @PostMapping("/send-comment-id/{commentId}/{recipeId}")
    ResponseEntity<Boolean> sendCommentId(@PathVariable String commentId, @PathVariable String recipeId);

    @GetMapping("/get-recipe-id/{recipeId}")
    ResponseEntity<String> getRecipeIdForIsExistRecipe(@PathVariable String recipeId);

    @PostMapping("/delete-comment-in-recipe-for-deleted-comment/{recipeId}/{commentId}")
    ResponseEntity<Boolean> deleteCommentInRecipeForDeletedComment(@PathVariable String recipeId, @PathVariable String commentId);

    @PostMapping("/send-point-id/{pointId}/{recipeId}")
    ResponseEntity<Boolean> sendPointId(@PathVariable String pointId, @PathVariable String recipeId);

    @PostMapping("/delete-point-in-recipe-for-deleted-point/{pointId}/{recipeId}")
    ResponseEntity<Boolean> deletePointInRecipeForDeletedPoint(@PathVariable String pointId,@PathVariable String recipeId);
}
