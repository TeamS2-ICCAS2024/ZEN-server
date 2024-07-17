package com.zen.ZenServer.api.baseHeart.dto.request;

import java.time.LocalDateTime;

public record BaseHeartSaveRequest(
        Long userId,
        int baseHeart,
        LocalDateTime measureTime
) {
}
