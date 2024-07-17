package com.zen.ZenServer.api.tetris.dto.response;


import com.zen.ZenServer.api.baseHeart.domain.BaseHeart;
import com.zen.ZenServer.api.baseHeart.dto.response.BaseHeartResponse;
import com.zen.ZenServer.api.tetris.domain.TetrisResult;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public record TetrisResultResponse (
        Long id,
        int score,
        int level,
        int lives,
        Duration playTime,
        BaseHeartResponse baseHeartRate,
        List<Integer> heartRateList,
        Double averageHearRate,
        LocalDateTime gameStartTime
) {
    public static TetrisResultResponse toResponse(TetrisResult tetrisResult) {
        BaseHeart baseHeart = tetrisResult.getBaseHeartRate();

        return new TetrisResultResponse(
                tetrisResult.getId(),
                tetrisResult.getScore(),
                tetrisResult.getLevel(),
                tetrisResult.getLives(),
                Duration.between(tetrisResult.getGameStartTime(), tetrisResult.getGameEndTime()),
                new BaseHeartResponse(baseHeart.getId(), baseHeart.getBaseHeart(), baseHeart.getMeasureTime()),
                tetrisResult.getHeartRateList(),
                tetrisResult.getHeartRateList().stream()
                    .mapToInt(Integer::intValue)
                    .average()
                    .orElse(0.0),
                tetrisResult.getGameStartTime()
        );
    }
}
