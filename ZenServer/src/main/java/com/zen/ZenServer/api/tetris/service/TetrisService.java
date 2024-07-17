package com.zen.ZenServer.api.tetris.service;

import com.zen.ZenServer.api.baseHeart.domain.BaseHeart;
import com.zen.ZenServer.api.baseHeart.repository.BaseHeartRepository;
import com.zen.ZenServer.api.tetris.domain.TetrisResult;
import com.zen.ZenServer.api.tetris.dto.request.TetrisSaveRequest;
import com.zen.ZenServer.api.tetris.dto.response.TetrisResultListResponse;
import com.zen.ZenServer.api.tetris.dto.response.TetrisResultResponse;
import com.zen.ZenServer.api.tetris.repository.TetrisRepository;
import com.zen.ZenServer.api.user.domain.User;
import com.zen.ZenServer.api.user.repository.UserRepository;
import com.zen.ZenServer.global.exception.CustomException;
import com.zen.ZenServer.global.response.enums.ErrorCode;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TetrisService {

    private final TetrisRepository tetrisRepository;
    private final BaseHeartRepository baseHeartRepository;
    private final UserRepository userRepository;

    public void saveTetrisResult(TetrisSaveRequest tetrisSaveRequest) {

        BaseHeart baseHeart = baseHeartRepository.findById(tetrisSaveRequest.baseHeartId())
                .orElseThrow(() -> new CustomException(ErrorCode.BASE_HEART_NOT_FOUND));

        User user = userRepository.findById(tetrisSaveRequest.userId())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        TetrisResult tetrisResult = TetrisResult.builder()
                .level(tetrisSaveRequest.level())
                .score(tetrisSaveRequest.score())
                .lives(tetrisSaveRequest.lives())
                .baseHeartRate(baseHeart)
                .heartRateList(tetrisSaveRequest.heartRateList())
                .gameStartTime(tetrisSaveRequest.gameStartTime())
                .gameEndTime(tetrisSaveRequest.gameEndTime())
                .user(user)
                .build();

        tetrisRepository.save(tetrisResult);
    }

    public List<TetrisResultListResponse> getTetrisResultList(Long userId, Integer year, Integer month) {
        List<TetrisResult> tetrisResults = tetrisRepository.findByYearAndMonth(year, month, userId);

        return tetrisResults.stream().map(TetrisResultListResponse::toResponse).collect(Collectors.toList());
    }

    public TetrisResultResponse getTetrisResult(Long gameId) {
        TetrisResult tetrisResult = tetrisRepository.findById(gameId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_GAME_RESULT));

        return TetrisResultResponse.toResponse(tetrisResult);
    }
}