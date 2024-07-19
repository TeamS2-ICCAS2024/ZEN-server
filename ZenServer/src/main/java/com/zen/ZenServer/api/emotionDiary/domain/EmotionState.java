package com.zen.ZenServer.api.emotionDiary.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EmotionState {
    SAD("SAD"),
    SOSO("SOSO"),
    ANGRY("ANGRY"),
    HAPPY("HAPPY");
    private final String emotion;
}
