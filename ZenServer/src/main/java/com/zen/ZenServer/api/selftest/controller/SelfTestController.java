package com.zen.ZenServer.api.selftest.controller;

import com.zen.ZenServer.api.selftest.dto.SelfTestDto;
import com.zen.ZenServer.api.selftest.service.SelfTestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "SelfTest", description = "셀프 테스트 관련 API 입니다.")
@RestController
@Transactional
@RequiredArgsConstructor
public class SelfTestController {
    private final SelfTestService selfTestService;

    @GetMapping("api/v1/selftest")
    @Operation(
            summary = "본인 셀프 테스트 리스트 정보 가져오기",
            description = "토큰을 기반으로 본인의 정보를 가져옵니다."
    )
    public List<SelfTestDto.ProvideInfo2> getUserInfo() {
        List<SelfTestDto.ProvideInfo2> info = selfTestService.getSelfTestInfo();
        return info;

    }

    @PostMapping("api/v1/selftest")
    @Operation(
            summary = "셀프 테스트 결과 정보 등록",
            description = "토큰을"
    )
    public void postSelfTest(SelfTestDto.ProvideInfo info) {
        selfTestService.postSelfTest(info);
    }



}