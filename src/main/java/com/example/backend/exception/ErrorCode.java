package com.example.backend.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // Common
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, 40001, "Invalid Input Value"),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, 40002, "Method Not Allowed"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 50000, "Server Error"),

    // User
    USER_ID_ALREADY_EXISTS(HttpStatus.CONFLICT, 40901, "User ID already exists"),
    NICKNAME_ALREADY_EXISTS(HttpStatus.CONFLICT, 40902, "Nickname already exists");


    private final HttpStatus status;
    private final int code;
    private final String message;
}
