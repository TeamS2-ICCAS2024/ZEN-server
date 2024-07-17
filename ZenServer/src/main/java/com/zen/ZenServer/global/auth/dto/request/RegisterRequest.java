package com.zen.ZenServer.global.auth.dto.request;


public record RegisterRequest(
        String nickname,
        String email,
        String password
) {
}
