package com.zen.ZenServer.api.baseHeart.dto.response;

import java.time.LocalDateTime;

public record BaseHeartResponse(
        Long baseHeartId,
        int baseHeart,
        LocalDateTime measureTime
){
}
