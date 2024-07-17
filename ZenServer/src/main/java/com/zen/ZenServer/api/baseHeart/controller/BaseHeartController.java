package com.zen.ZenServer.api.baseHeart.controller;

import com.zen.ZenServer.api.baseHeart.dto.request.BaseHeartSaveRequest;
import com.zen.ZenServer.api.baseHeart.dto.response.BaseHeartResponse;
import com.zen.ZenServer.api.baseHeart.service.BaseHeartService;
import com.zen.ZenServer.global.response.ApiResponseDto;
import com.zen.ZenServer.global.response.enums.SuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BaseHeartController {
    private final BaseHeartService baseHeartService;

    @PostMapping("/v1/base/save")
    public ApiResponseDto<BaseHeartResponse> saveBaseHeart(@RequestBody BaseHeartSaveRequest baseHeartSaveRequest) {

        return ApiResponseDto.success(SuccessCode.HEART_SAVE_BASE_SUCCESS, baseHeartService.saveBaseHeart(baseHeartSaveRequest));
    }

    @GetMapping("/v1/base/latest/user/{userId}")
    public ApiResponseDto<BaseHeartResponse> getLatestBaseHeartByUserId(@PathVariable(value = "userId") Long userId) {

        return ApiResponseDto.success(SuccessCode.HEART_SAVE_BASE_SUCCESS, baseHeartService.getLatestBaseHeartByUserId(userId));
    }
}
