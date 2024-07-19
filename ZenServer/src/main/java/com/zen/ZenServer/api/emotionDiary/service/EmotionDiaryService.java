package com.zen.ZenServer.api.emotionDiary.service;

import com.zen.ZenServer.api.emotionDiary.domain.EmotionDiary;
import com.zen.ZenServer.api.emotionDiary.dto.request.EmotionDiaryPostRequest;
import com.zen.ZenServer.api.emotionDiary.dto.response.EmotionDiaryDetailGetResponse;
import com.zen.ZenServer.api.emotionDiary.dto.response.EmotionDiaryListGetResponse;
import com.zen.ZenServer.api.emotionDiary.repository.EmotionDiaryRepository;
import com.zen.ZenServer.global.Util.DateUtil;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmotionDiaryService {

    private final EmotionDiaryRepository emotionDiaryRepository;

    @Transactional
    public void postEmotionDiary(Long userId, EmotionDiaryPostRequest request, String gptSummary, String gptAnswer) {

        EmotionDiary emotionDiary = EmotionDiary.builder()
                .userId(userId)
                .userCharacter(request.character())
                .inputText(request.userInput())
                .gptSummary(gptSummary)
                .gptAnswer(gptAnswer)
                .emotionState(request.emotionState())
                .build();

        emotionDiaryRepository.save(emotionDiary);
    }

    @Transactional(readOnly = true)
    public List<EmotionDiaryListGetResponse> getEmotionDiaryList(Long userId) {
        List<EmotionDiary> emotionDiaryList = emotionDiaryRepository.findByUserId(userId);

       return emotionDiaryList.stream()
                .map(oneEmotionDiary ->
                        EmotionDiaryListGetResponse.builder()
                        .character(oneEmotionDiary.getUserCharacter())
                        .date(DateUtil.refineDate(oneEmotionDiary.getCreatedAt()))
                        .emotionDiaryId(oneEmotionDiary.getId())
                        .build())
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public EmotionDiaryDetailGetResponse getEmotionDiaryDetail(Long emotionDiaryId){
        EmotionDiary emotionDiary = emotionDiaryRepository.findByIdOrThrow(emotionDiaryId);

        return EmotionDiaryDetailGetResponse.builder()
                .emotion(emotionDiary.getEmotionState())
                .summary(emotionDiary.getGptSummary())
                .solution(emotionDiary.getGptAnswer())
                .when(DateUtil.refineDate(emotionDiary.getCreatedAt()))
                .build();
    }
}
