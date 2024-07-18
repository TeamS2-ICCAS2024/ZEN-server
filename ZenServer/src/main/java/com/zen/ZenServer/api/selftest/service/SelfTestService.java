package com.zen.ZenServer.api.selftest.service;

import com.zen.ZenServer.api.selftest.domain.SelfTest;
import com.zen.ZenServer.api.selftest.dto.SelfTestDto;
import com.zen.ZenServer.api.selftest.repository.SelfTestRepository;
import com.zen.ZenServer.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SelfTestService {
    private final SelfTestRepository selfTestRepository;
    private final UserRepository userRepository;
    public List<SelfTestDto.ProvideInfo2> getSelfTestInfo() {
        List<SelfTest> byUserId = selfTestRepository.findByUserId(1L);
        return byUserId.stream().map(selfTest -> SelfTestDto.ProvideInfo2.builder()
                .id(selfTest.getId())
                .userId(selfTest.getUserId())
                .score(selfTest.getScore())
                .createdAt(selfTest.getCreatedAt())
                .build()).toList();

    }

    public void postSelfTest(SelfTestDto.ProvideInfo info) {
        SelfTest selfTest = SelfTest.builder()
                .userId(1L)
                .score(info.getScore())
                .createdAt(LocalDateTime.now())
                .build();
        selfTestRepository.save(selfTest);
        userRepository.findById(1L).get().setLastTestAt(LocalDateTime.now());
    }
}
