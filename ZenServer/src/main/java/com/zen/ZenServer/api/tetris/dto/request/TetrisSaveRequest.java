package com.zen.ZenServer.api.tetris.dto.request;

import java.time.LocalDateTime;
import java.util.List;

public record TetrisSaveRequest (

        Long userId,
        List<Integer> heartRateList,
        Long baseHeartId,
        int level,
        int score,
        int lives,
        LocalDateTime gameStartTime,
        LocalDateTime gameEndTime
) {
}
