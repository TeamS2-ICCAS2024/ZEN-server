package com.zen.ZenServer.api.emotionDiary.controller;

import com.zen.ZenServer.api.emotionDiary.dto.request.EmotionDiaryPostRequest;
import com.zen.ZenServer.api.emotionDiary.dto.response.EmotionDiaryListGetResponse;
import com.zen.ZenServer.api.emotionDiary.service.EmotionDiaryService;
import com.zen.ZenServer.global.client.gpt.GptService;
import com.zen.ZenServer.global.response.ApiResponseDto;
import com.zen.ZenServer.global.response.enums.SuccessCode;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
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

    Long userId = 1L;

    @PostMapping("/emotion-diary")
    public ApiResponseDto postEmotionDiary(@RequestBody EmotionDiaryPostRequest request) {

        String gptAnswer = gptService.getAnswer(request);

        emotionDiaryService.postEmotionDiary(userId,request,gptAnswer);
        return ApiResponseDto.success(SuccessCode.EMOTIONDIARY_POST_SUCCESS);
    }

    @GetMapping("/diary-list")
    public ApiResponseDto<List<EmotionDiaryListGetResponse>> getDiaryList() {
        return ApiResponseDto.success(SuccessCode.EMOTIONDIARY_LIST_GET_SUCCESS,emotionDiaryService.getEmotionDiaryList(userId));
    }
}
