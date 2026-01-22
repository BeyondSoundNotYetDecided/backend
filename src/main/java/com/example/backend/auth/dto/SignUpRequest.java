package com.example.backend.auth.dto;

import com.example.backend.auth.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
    private String password;
    private String nickname;
    private String email;

    public User toEntity() {
        return User.builder()
                .password(password)
                .nickname(nickname)
                .email(email)
                .build();
    }
}
