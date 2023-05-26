package com.emre.controller;

import com.emre.dto.request.*;
import com.emre.repository.entity.Auth;
import com.emre.service.AuthService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.emre.constant.EndPoints.*;

@RestController
@RequestMapping(AUTH)
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping(REGISTER)
    public ResponseEntity<Auth> register(@RequestBody @Valid RegisterRequestDto dto) {
        return ResponseEntity.ok(authService.register(dto));
    }

    @PostMapping(ACTIVATE_STATUS)
    public ResponseEntity<String> activateStatus(@RequestBody ActivateStatusRequestDto dto) {
        return ResponseEntity.ok(authService.activateStatus(dto));
    }

    @PostMapping(LOGIN)
    public ResponseEntity<String> login(@RequestBody LoginRequestDto dto) {
        return ResponseEntity.ok(authService.login(dto));
    }

    @PostMapping(FORGOT_PASS)
    public ResponseEntity<String> forgotPassword(String email, String username) {
        return ResponseEntity.ok(authService.forgotPassword(email, username));
    }

    @GetMapping(FIND_ALL)
    public ResponseEntity<List<Auth>> findAll() {
        return ResponseEntity.ok(authService.findAll());
    }

    //===============================================

    @PostMapping("/update-user-to-auth")
    @Hidden
    public ResponseEntity<Boolean> updateUserFromUserToAuth(@RequestBody UpdateUserFromUserToAuth dto) {
        return ResponseEntity.ok(authService.updateUserFromUserToAuth(dto));
    }

    @PutMapping("/change-password")
    @Hidden
    public ResponseEntity<Boolean> changePassword(@RequestBody ChangePassForUserToAuthDto dto) {
        return ResponseEntity.ok(authService.changePassword(dto));
    }

}
