package com.zen.ZenServer.api;

import com.zen.ZenServer.global.response.ApiResponseDto;
import com.zen.ZenServer.global.response.enums.SuccessCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/test")
public class TestController {

    @GetMapping("/responseEntity")
    public ResponseEntity<String> getResponseEntity() {
        return ResponseEntity.ok("ok");
    }
    @GetMapping("api-response-dto")
    public ApiResponseDto<SuccessCode> getApiResponseDto() {
        return ApiResponseDto.success(SuccessCode.TEST_SUCCESS);
    }
    @GetMapping("api-response-dto-with-data")
    public ApiResponseDto<String> getApiResponseDtoWithData() {
        return ApiResponseDto.success(SuccessCode.TEST_SUCCESS,"test");
    }
}
