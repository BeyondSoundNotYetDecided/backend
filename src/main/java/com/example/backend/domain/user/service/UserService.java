package com.example.backend.domain.user.service;

import com.example.backend.domain.user.entity.User;
import com.example.backend.domain.user.repository.UserRepository;
import com.example.backend.global.exception.CustomException;
import com.example.backend.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User createUser(User user) {
        // 유저 회원가입 전 email로 중복 체크
        if (userRepository.existsByEmail(user.getEmail())) {
            // 중복아이디 예외처리
            throw new CustomException(ErrorCode.USER_ID_ALREADY_EXISTS);
        }
        if (userRepository.existsByNickname(user.getNickname())) {
            // 중복닉네임 예외처리
            throw new CustomException(ErrorCode.NICKNAME_ALREADY_EXISTS);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }


}
