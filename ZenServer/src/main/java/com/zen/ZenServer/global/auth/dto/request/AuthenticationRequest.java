package com.zen.ZenServer.global.auth.dto.request;

public record AuthenticationRequest(
        String email,
        String password
) {
}
