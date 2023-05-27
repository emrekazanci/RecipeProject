package com.emre.manager;

import com.emre.dto.response.GetUserForFavCategory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Set;

@FeignClient(url = "http://localhost:8090/api/v1/user",name = "recipe-userprofile")
public interface IUserManager {
    @GetMapping("/get-user-with-fav-category/{categoryId}")
    ResponseEntity<Set<GetUserForFavCategory>> getUserWithFavCategory(@PathVariable List<String> categoryId);
}
