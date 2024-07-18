package com.zen.ZenServer.api.selftest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SelfTestDto {
    @Builder
    @Data
    @Schema(description = "회원 가입시 넘겨주는 정보")
    public static class ProvideInfo {
        private Long score;
    }

    @Builder
    @Data
    @Schema(description = "회원 가입시 넘겨주는 정보")
    public static class ProvideInfo2 {
        private Long id;

        private Long userId;

        private Long score;

        private LocalDateTime createdAt;
    }



}
