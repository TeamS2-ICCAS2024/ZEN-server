package com.zen.ZenServer.api.emotionDiary.service;

import com.zen.ZenServer.api.emotionDiary.domain.EmotionDiary;
import com.zen.ZenServer.api.emotionDiary.dto.request.DiaryPostRequest;
import com.zen.ZenServer.api.emotionDiary.dto.response.DiaryDetailGetResponse;
import com.zen.ZenServer.api.emotionDiary.dto.response.DiaryListGetResponse;
import com.zen.ZenServer.api.emotionDiary.repository.EmotionDiaryRepository;
import com.zen.ZenServer.global.Util.DateUtil;
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
    public void postEmotionDiary(Long userId, DiaryPostRequest request, String gptSummary, String gptAnswer) {

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
    public List<DiaryListGetResponse> getEmotionDiaryList(Long userId) {
        List<EmotionDiary> emotionDiaryList = emotionDiaryRepository.findByUserId(userId);

       return emotionDiaryList.stream()
                .map(oneEmotionDiary ->
                        DiaryListGetResponse.builder()
                        .character(oneEmotionDiary.getUserCharacter())
                        .date(DateUtil.refineDate(oneEmotionDiary.getCreatedAt()))
                        .emotionDiaryId(oneEmotionDiary.getId())
                        .build())
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public DiaryDetailGetResponse getEmotionDiaryDetail(Long emotionDiaryId){
        EmotionDiary emotionDiary = emotionDiaryRepository.findByIdOrThrow(emotionDiaryId);

        return DiaryDetailGetResponse.builder()
                .emotion(emotionDiary.getEmotionState())
                .summary(emotionDiary.getGptSummary())
                .solution(emotionDiary.getGptAnswer())
                .when(DateUtil.refineDate(emotionDiary.getCreatedAt()))
                .build();
    }

    @Transactional(readOnly = true)
    public List<String> getDiaryByEmotion(String emotion, Long userId){
        List<EmotionDiary> emotionDiaryList = emotionDiaryRepository.findTop4ByEmotionStateAndUserId(emotion,userId);
        return emotionDiaryList.stream().
                map(oneEmotionDiary->DateUtil.refineDate(oneEmotionDiary.getCreatedAt()))
                .toList();
    }
}
