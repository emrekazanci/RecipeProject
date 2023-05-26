package com.emre.manager;

import com.emre.dto.request.ChangePassForUserToAuthDto;
import com.emre.dto.request.UpdateUserFromUserToAuth;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "http://localhost:8060/api/v1/auth",name = "userprofile-auth")
public interface IAuthManager {
    @PostMapping("/update-user-to-auth")
    ResponseEntity<Boolean> updateUserFromUserToAuth(@RequestBody UpdateUserFromUserToAuth dto);

    @PutMapping("/change-password")
    ResponseEntity<Boolean> changePassword(@RequestBody ChangePassForUserToAuthDto dto);
}
