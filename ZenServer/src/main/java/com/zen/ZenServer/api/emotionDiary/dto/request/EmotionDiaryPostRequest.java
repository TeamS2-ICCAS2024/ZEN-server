package com.zen.ZenServer.api.emotionDiary.dto.request;

public record EmotionDiaryPostRequest(
        String	userInput,      //	사용자 입력 답변 요약한 내용
        String	character,      //	캐릭터
        String	emotionState	//  감정상태
){
}