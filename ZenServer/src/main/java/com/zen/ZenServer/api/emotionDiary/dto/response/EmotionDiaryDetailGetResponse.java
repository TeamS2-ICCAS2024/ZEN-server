package com.zen.ZenServer.api.emotionDiary.dto.response;

import lombok.Builder;

@Builder
public record EmotionDiaryDetailGetResponse (
        String when,	//날짜
        String emotion,	//감정
        String	summary,	//사용자가 입력한 내용 요약
        String	solution	//gpt가 제공한 답변
){
}
