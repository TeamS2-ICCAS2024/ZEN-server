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

    // 404 Not Found
    NOT_FOUND_END_POINT(40400, HttpStatus.NOT_FOUND, "존재하지 않는 API입니다."),
    USER_NOT_FOUND(40401, HttpStatus.NOT_FOUND, "해당 유저는 존재하지 않습니다."),

    // 405 Method Not Allowed Error
    METHOD_NOT_ALLOWED(40500, HttpStatus.METHOD_NOT_ALLOWED, "지원하지 않는 메소드입니다."),

    // 500 Internal Server Error
    INTERNAL_SERVER_ERROR(50000, HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류입니다."),
    PRESIGNED_URL_GET_ERROR(50001, HttpStatus.INTERNAL_SERVER_ERROR, "S3 presigned url을 받아오기에 실패했습니다."),
    IMAGE_DELETE_ERROR(50002, HttpStatus.INTERNAL_SERVER_ERROR, "S3 이미지 삭제가 실패했습니다.");

    private final int code;
    private final HttpStatus httpStatus;
    private final String message;
}
