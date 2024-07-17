package com.zen.ZenServer.global.auth.dto.response;

import lombok.Builder;

@Builder
public record AuthenticationResponse(
        Long userId,
        String accessToken,
        String refreshToken
) {
}
