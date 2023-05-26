package com.emre.manager;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(url = "http://localhost:8070/api/v1/comment",name = "recipe-comment")
public interface ICommentManager {
    @PostMapping("/delete-comment-for-deleted-recipe/{recipeId}")
    ResponseEntity<Boolean>  deleteCommentForDeletedRecipe(@PathVariable String recipeId);

}
