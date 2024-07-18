package com.zen.ZenServer.api.emotionDiary.service;

import com.zen.ZenServer.api.emotionDiary.domain.EmotionDiary;
import com.zen.ZenServer.api.emotionDiary.dto.EmotionDiaryPostRequest;
import com.zen.ZenServer.api.emotionDiary.repository.EmotionDiaryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmotionDiaryService {

    private final EmotionDiaryRepository emotionDiaryRepository;
    //private final OpenAiService openAiService;

    @Transactional
    public void postEmotionDiary(Long userId, EmotionDiaryPostRequest request, String gptAnswer) {

        EmotionDiary emotionDiary = EmotionDiary.builder()
                .userId(userId)
                .userCharacter(request.character())
                .inputText(request.userInput())
                .gptAnswer(gptAnswer)
                .build();

        emotionDiaryRepository.save(emotionDiary);
    }
}
