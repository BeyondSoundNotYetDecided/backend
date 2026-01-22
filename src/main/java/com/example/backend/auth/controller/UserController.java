package com.example.backend.auth.controller;

import com.example.backend.auth.dto.SignUpRequest;
import com.example.backend.auth.service.UserService;
import com.example.backend.common.ApiResponse;
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

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<String>> signup(@RequestBody SignUpRequest signUpRequest) {
        userService.createUser(signUpRequest.toEntity());
        return ResponseEntity.ok(ApiResponse.success("User registered successfully"));
    }
}
