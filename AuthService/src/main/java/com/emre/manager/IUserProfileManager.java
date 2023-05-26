package com.emre.manager;

import com.emre.dto.request.CreateUserForAuthToUserDto;
import com.emre.dto.request.UpdatePassToUserProfileForForgotPass;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "auth-userprofile", url = "http://localhost:8090/api/v1/user")
public interface IUserProfileManager {
    @PostMapping("/create")
    ResponseEntity<Boolean> createUser(@RequestBody CreateUserForAuthToUserDto dto);
    @PutMapping("/forgot-password-user")
    ResponseEntity<Boolean> updatePassForForgotPass(@RequestBody UpdatePassToUserProfileForForgotPass dto);
}
