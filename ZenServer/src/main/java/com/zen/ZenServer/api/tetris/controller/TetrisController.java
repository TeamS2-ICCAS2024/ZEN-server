package com.zen.ZenServer.api.tetris.controller;

import com.zen.ZenServer.api.tetris.dto.request.TetrisSaveRequest;
import com.zen.ZenServer.api.tetris.dto.response.TetrisResultListResponse;
import com.zen.ZenServer.api.tetris.dto.response.TetrisResultResponse;
import com.zen.ZenServer.api.tetris.service.TetrisService;
import com.zen.ZenServer.global.response.ApiResponseDto;
import com.zen.ZenServer.global.response.enums.SuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TetrisController {

    private final TetrisService tetrisService;

    @PostMapping("/v1/tetris/save")
    public ApiResponseDto<Void> saveTetrisResult(@RequestBody TetrisSaveRequest tetrisSaveRequest) {
        tetrisService.saveTetrisResult(tetrisSaveRequest);

        return ApiResponseDto.success(SuccessCode.TETRIS_SAVE_GAME_RESULT_SUCCESS);
    }

    @GetMapping("/v1/tetris/list/year/{year}/month/{month}/user/{userId}")
    public ApiResponseDto<List<TetrisResultListResponse>> getTetrisResultListByYearMonth(
            @PathVariable("year") Integer year,
            @PathVariable("month") Integer month,
            @PathVariable("userId") Long userId
    ) {
        return ApiResponseDto.success(SuccessCode.TETRIS_GET_RESULT_LIST_SUCCESS, tetrisService.getTetrisResultList(userId, year, month));
    }

    @GetMapping("/v1/tetris/game/{gameId}")
    public ApiResponseDto<TetrisResultResponse> getTetrisResult(@PathVariable("gameId") Long gameId) {
        return ApiResponseDto.success(SuccessCode.TETRIS_GET_RESULT_SUCCESS, tetrisService.getTetrisResult(gameId));
    }
}
