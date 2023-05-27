package com.emre.controller;

import com.emre.dto.request.ChangePasswordRequestDto;
import com.emre.dto.request.CreateUserForAuthToUserDto;
import com.emre.dto.request.UpdatePassToUserProfileForForgotPass;
import com.emre.dto.request.UpdateUserRequestDto;
import com.emre.dto.response.GetUserForFavCategory;
import com.emre.dto.response.GetUserIdAndUsernameDto;
import com.emre.repository.entity.User;
import com.emre.service.UserService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

import static com.emre.constant.EndPoints.*;

@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping(UPDATE + "/{token}")
    public ResponseEntity<User> update(@PathVariable String token, @RequestBody UpdateUserRequestDto dto) {
        return ResponseEntity.ok(userService.update(token, dto));
    }

    @PutMapping(CHANGE_PASS + "/{token}")
    public ResponseEntity<String> changePassword(@PathVariable String token, @RequestBody ChangePasswordRequestDto dto) {
        return ResponseEntity.ok(userService.changePassword(token, dto));
    }

    @PostMapping(SAVE_FAVORITE_RECIPE + "/{token}/{recipeId}")
    public ResponseEntity<Boolean> saveFavoriteRecipe(@PathVariable String token, @PathVariable String recipeId) {
        return ResponseEntity.ok(userService.saveFavoriteRecipe(token, recipeId));
    }

    @GetMapping(FIND_USER_BY_CATEGORY)
    public ResponseEntity<List<User>> findUserByCategoryId(String categoryId) {
        return ResponseEntity.ok(userService.findUserByCategoryId(categoryId));
    }

    @GetMapping(FIND_ALL)
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    //=============================
    @PostMapping("/create")
    @Hidden
    public ResponseEntity<Boolean> createUser(@RequestBody CreateUserForAuthToUserDto dto) {
        return ResponseEntity.ok(userService.createUser(dto));
    }

    @PutMapping("/forgot-password-user")
    @Hidden
    public ResponseEntity<Boolean> forgotPassword(@RequestBody UpdatePassToUserProfileForForgotPass updatePassToUserProfileForForgotPass) {
        return ResponseEntity.ok(userService.forgotPassword(updatePassToUserProfileForForgotPass));
    }

    @GetMapping("/get-id-username/{authId}")
    @Hidden
    ResponseEntity<GetUserIdAndUsernameDto> getUserIdAndUserNameWithAuthId(@PathVariable Long authId) {
        return ResponseEntity.ok(userService.getUserIdAndUserNameWithAuthId(authId));
    }

    @GetMapping("/get-user-with-fav-category/{categoryId}")
    @Hidden
    public ResponseEntity<Set<GetUserForFavCategory>> getUserWithFavCategory(@PathVariable List<String> categoryId) {
        return ResponseEntity.ok(userService.getUserWithFavCategory(categoryId));
    }
}
