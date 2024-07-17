package com.zen.ZenServer.api.tetris.dto.response;


import com.zen.ZenServer.api.tetris.domain.TetrisResult;

import java.time.LocalDateTime;

public record TetrisResultListResponse(
        Long id,
        int lives,
        LocalDateTime startTime
) {
    public static TetrisResultListResponse toResponse(TetrisResult tetrisResult) {
        return new TetrisResultListResponse(tetrisResult.getId(), tetrisResult.getLives(), tetrisResult.getGameStartTime());
    }
}
