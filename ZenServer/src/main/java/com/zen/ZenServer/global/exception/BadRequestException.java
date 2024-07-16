package com.zen.ZenServer.global.exception;


import com.zen.ZenServer.global.response.enums.ErrorCode;

public class BadRequestException extends CustomException {
    public BadRequestException(ErrorCode errorCode) {
        super(errorCode);
    }
}
