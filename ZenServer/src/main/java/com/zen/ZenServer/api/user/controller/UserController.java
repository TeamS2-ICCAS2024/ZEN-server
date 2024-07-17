package com.zen.ZenServer.api.user.controller;

import com.zen.ZenServer.api.user.dto.UserDto;
import com.zen.ZenServer.api.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User", description = "유저 관련 API 입니다.")
@RestController
@Transactional
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/user")
    @Operation(
            summary = "본인 정보 가져오기",
            description = "토큰을 기반으로 본인의 정보를 가져옵니다."
    )
    public UserDto.ProvideInfo getUserInfo() {
        UserDto.ProvideInfo info = userService.getUserInfo();
        return info;
    }

    @GetMapping("/background")
    @Operation(
            summary = "배경을 변경합니다.",
            description = "배경을 변경합니다."
    )
    public UserDto.Result changeBackground(Long background_id) {
        userService.changeBackground(background_id);
        return UserDto.Result.builder().result("success").build();
    }
}