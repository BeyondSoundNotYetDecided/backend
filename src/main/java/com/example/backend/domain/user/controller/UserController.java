package com.example.backend.domain.user.controller;

import com.example.backend.domain.user.dto.SignUpRequest;
import com.example.backend.domain.user.service.UserService;
import com.example.backend.global.auth.dto.LoginRequest;
import com.example.backend.global.auth.dto.TokenResponse;
import com.example.backend.global.auth.service.AuthService;
import com.example.backend.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<String>> signup(@RequestBody SignUpRequest signUpRequest) {
        userService.createUser(signUpRequest);
        return ResponseEntity.ok(ApiResponse.success("User registered successfully"));
    }

    // 로그인 API
    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest request) {
        TokenResponse tokenResponse = authService.login(request);
        return ResponseEntity.ok(tokenResponse);
    }
}
