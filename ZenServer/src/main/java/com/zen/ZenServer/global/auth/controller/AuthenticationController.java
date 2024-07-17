package com.zen.ZenServer.global.auth.controller;

import com.zen.ZenServer.global.auth.dto.request.AuthenticationRequest;
import com.zen.ZenServer.global.auth.dto.request.RegisterRequest;
import com.zen.ZenServer.global.auth.dto.response.AuthenticationResponse;
import com.zen.ZenServer.global.auth.service.AuthenticationService;
import com.zen.ZenServer.global.response.ApiResponseDto;
import com.zen.ZenServer.global.response.enums.SuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/v1/auth/register")
    public ApiResponseDto<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest) {

        return ApiResponseDto.success(SuccessCode.AUTH_REGISTER_SUCCESS, authenticationService.register(registerRequest));
    }

    @PostMapping("/v1/auth/authenticate")
    public ApiResponseDto<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {

        return  ApiResponseDto.success(SuccessCode.AUTH_AUTHENTICATE_SUCCESS, authenticationService.authenticate(authenticationRequest));
    }
}
