package com.zen.ZenServer.global.response.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // 400 Bad Request
    BAD_REQUEST(40000, HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    MISSING_REQUIRED_HEADER(40001, HttpStatus.BAD_REQUEST, "필수 헤더가 누락되었습니다."),
    MISSING_REQUIRED_PARAMETER(40002, HttpStatus.BAD_REQUEST, "필수 파라미터가 누락되었습니다."),

    // 401 Unauthorized
    UNAUTHORIZED_MEMBER(40101, HttpStatus.UNAUTHORIZED, "계정 정보가 존재하지 않습니다"),
    WRONG_PASSWORD(40102, HttpStatus.UNAUTHORIZED, "비밀번호가 틀렸습니다"),
    EXPIRED_TOKEN(40103, HttpStatus.UNAUTHORIZED, "만료된 토큰입니다"),
    INVALID_TOKEN(40104, HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰입니다"),
    EMPTY_TOKEN(40105, HttpStatus.UNAUTHORIZED, "토큰이 비어있습니다." ),

    // 403 FORBIDDEN
    INVALID_AUTH_USER(40301, HttpStatus.FORBIDDEN, "권한 정보가 없는 사용자입니다"),
    ACCESS_DENIED(40302,  HttpStatus.FORBIDDEN, "접근 권한이 없습니다"),

    // 404 Not Found
    NOT_FOUND_END_POINT(40400, HttpStatus.NOT_FOUND, "존재하지 않는 API입니다."),
    USER_NOT_FOUND(40401, HttpStatus.NOT_FOUND, "해당 유저는 존재하지 않습니다."),
    BASE_HEART_NOT_FOUND(40402, HttpStatus.NOT_FOUND, "base 심박이 존재하지 않습니다."),
    NOT_FOUND_GAME_RESULT(40403, HttpStatus.NOT_FOUND, "게임 결과가 존재하지 않습니다." ),

    // 405 Method Not Allowed Error
    METHOD_NOT_ALLOWED(40500, HttpStatus.METHOD_NOT_ALLOWED, "지원하지 않는 메소드입니다."),

    // 409 Conflict
    DUPLICATED_EMAIL_USER(40900, HttpStatus.CONFLICT, "존재하는 이메일입니다."),

    // 500 Internal Server Error
    INTERNAL_SERVER_ERROR(50000, HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류입니다."),
    PRESIGNED_URL_GET_ERROR(50001, HttpStatus.INTERNAL_SERVER_ERROR, "S3 presigned url을 받아오기에 실패했습니다."),
    IMAGE_DELETE_ERROR(50002, HttpStatus.INTERNAL_SERVER_ERROR, "S3 이미지 삭제가 실패했습니다."),
    GPT_SERVER_ERROR(50003, HttpStatus.INTERNAL_SERVER_ERROR,"gpt통신 중 에러가 발생했습니다.");

    private final int code;
    private final HttpStatus httpStatus;
    private final String message;
}
