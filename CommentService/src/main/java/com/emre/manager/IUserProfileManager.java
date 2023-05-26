package com.emre.manager;

import com.emre.dto.response.GetUserIdAndUsernameDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://localhost:8090/api/v1/user",name = "comment-userprofile")
public interface IUserProfileManager {

    @GetMapping("/get-id-username/{authId}")
    ResponseEntity<GetUserIdAndUsernameDto> getUserIdAndUserNameWithAuthId(@PathVariable Long authId);
}
