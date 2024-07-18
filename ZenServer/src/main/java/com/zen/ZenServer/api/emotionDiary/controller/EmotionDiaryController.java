package com.zen.ZenServer.api.emotionDiary.controller;

import com.zen.ZenServer.api.emotionDiary.dto.EmotionDiaryPostRequest;
import com.zen.ZenServer.api.emotionDiary.service.EmotionDiaryService;
import com.zen.ZenServer.global.client.gpt.GptService;
import com.zen.ZenServer.global.response.ApiResponseDto;
import com.zen.ZenServer.global.response.enums.SuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class EmotionDiaryController {

    private final EmotionDiaryService emotionDiaryService;
    private final GptService gptService;

    @PostMapping("/emotion-diary")
    public ApiResponseDto postEmotionDiary(@RequestBody EmotionDiaryPostRequest request) {
        Long userId = 1L;

        String gptAnswer = gptService.getAnswer(request);
        // 응답 데이터 자르기
        if (gptAnswer.length() > 255) {
            gptAnswer = gptAnswer.substring(0, 255);
        }
        emotionDiaryService.postEmotionDiary(userId,request,gptAnswer);
        return ApiResponseDto.success(SuccessCode.EMOTIONDIARY_POST_SUCCESS);
    }
}
