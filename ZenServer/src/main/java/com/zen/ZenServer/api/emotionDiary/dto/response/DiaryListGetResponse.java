package com.zen.ZenServer.api.emotionDiary.dto.response;

import lombok.Builder;

@Builder
public record DiaryListGetResponse(
        String character,
        String date,
        Long emotionDiaryId
) {
}
