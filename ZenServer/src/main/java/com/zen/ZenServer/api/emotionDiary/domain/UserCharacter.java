package com.zen.ZenServer.api.emotionDiary.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserCharacter {

    BAO("BAO"),
    SKY("SKY"),
    MOZZI("MOZZI"),
    ;

    private final String name;
}
