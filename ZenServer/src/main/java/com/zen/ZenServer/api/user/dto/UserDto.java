package com.zen.ZenServer.api.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
public class UserDto {
    @Builder
    @Data
    @Schema(description = "회원 가입시 넘겨주는 정보")
    public static class ProvideInfo {
        private Long id;
        private String nickname;
        private Long leaf;
        private Long background_id;
    }

    @Builder
    @Data
    @Schema(description = "정보")
    public static class Result {
        private String result;
    }

}
