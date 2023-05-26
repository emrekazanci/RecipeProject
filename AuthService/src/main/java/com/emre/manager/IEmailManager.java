package com.emre.manager;

import com.emre.dto.response.ForgotPasswordToMailResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "http://localhost:8085/api/v1/mail",name = "auth-mail")
public interface IEmailManager {
    @PostMapping("/forgot-password")
    ResponseEntity<Boolean> forgotPasswordToMail(@RequestBody ForgotPasswordToMailResponseDto dto);
}
