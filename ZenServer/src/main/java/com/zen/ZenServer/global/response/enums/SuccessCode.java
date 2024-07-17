package com.zen.ZenServer.global.response.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessCode {

    // 200 OK
    TEST_SUCCESS(20000, HttpStatus.CREATED, "test 성공"),

    AUTH_REGISTER_SUCCESS(200, HttpStatus.OK, "회원가입 성공"),
    AUTH_AUTHENTICATE_SUCCESS(200, HttpStatus.OK, "로그인(사용자 인증) 성공");

    // 201 Created
    private final int code;
    private final HttpStatus httpStatus;
    private final String message;
}
