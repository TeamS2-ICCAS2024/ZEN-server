package com.zen.ZenServer.api.emotionDiary.controller;

import com.zen.ZenServer.api.emotionDiary.dto.request.DiaryPostRequest;
import com.zen.ZenServer.api.emotionDiary.dto.response.DiaryDetailGetResponse;
import com.zen.ZenServer.api.emotionDiary.dto.response.DiaryListGetResponse;
import com.zen.ZenServer.api.emotionDiary.service.EmotionDiaryService;
import com.zen.ZenServer.global.client.gpt.GptService;
import com.zen.ZenServer.global.response.ApiResponseDto;
import com.zen.ZenServer.global.response.enums.SuccessCode;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class EmotionDiaryController {

    private final EmotionDiaryService emotionDiaryService;
    private final GptService gptService;

    Long userId = 1L;

    @PostMapping("/emotion-diary")
    public ApiResponseDto postEmotionDiary(@RequestBody DiaryPostRequest request) {

        String gptSummary = gptService.getSummary(request);
        String gptAnswer = gptService.getAnswer(gptSummary,request);

        emotionDiaryService.postEmotionDiary(userId,request,gptSummary,gptAnswer);
        return ApiResponseDto.success(SuccessCode.EMOTIONDIARY_POST_SUCCESS);
    }

    @GetMapping("/diary-list")
    public ApiResponseDto<List<DiaryListGetResponse>> getDiaryList() {
        return ApiResponseDto.success(SuccessCode.EMOTIONDIARY_LIST_GET_SUCCESS,emotionDiaryService.getEmotionDiaryList(userId));
    }

    @GetMapping("/emotion-diary/{emotionDiaryId}")
    public ApiResponseDto<DiaryDetailGetResponse> getDiaryById(
            @PathVariable Long emotionDiaryId
    ) {
        return ApiResponseDto.success(SuccessCode.EMOTIONDIARY_DETAIL_GET_SUCCESS,
                emotionDiaryService.getEmotionDiaryDetail(emotionDiaryId));
    }

    @GetMapping("/diary")
    public ApiResponseDto<List<String>> getDiaryByEmotion(@RequestParam String emotion) {
        return ApiResponseDto.success(SuccessCode.EMOTIONDIARY_GET_BY_EMOTION_SUCCESS, emotionDiaryService.getDiaryByEmotion(emotion,userId));
    }
}
