package com.emre.controller;

import com.emre.dto.response.ForgotPasswordToMailResponseDto;
import com.emre.service.MailService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/mail")
@RequiredArgsConstructor
public class MailController {
    private final MailService mailService;

    @PostMapping("/forgot-password")
    @Hidden
    public ResponseEntity<Boolean> forgotPasswordToMail(@RequestBody ForgotPasswordToMailResponseDto dto) {
        return ResponseEntity.ok(mailService.forgotPasswordToMail(dto));
    }
}
