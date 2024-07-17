package com.zen.ZenServer.api.baseHeart.service;

import com.zen.ZenServer.api.baseHeart.domain.BaseHeart;
import com.zen.ZenServer.api.baseHeart.dto.request.BaseHeartSaveRequest;
import com.zen.ZenServer.api.baseHeart.dto.response.BaseHeartResponse;
import com.zen.ZenServer.api.baseHeart.repository.BaseHeartRepository;
import com.zen.ZenServer.api.user.domain.User;
import com.zen.ZenServer.api.user.repository.UserRepository;
import com.zen.ZenServer.global.exception.CustomException;
import com.zen.ZenServer.global.response.enums.ErrorCode;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BaseHeartService {

    private final UserRepository userRepository;
    private final BaseHeartRepository baseHeartRepository;

    public BaseHeartResponse saveBaseHeart(BaseHeartSaveRequest baseHeartSaveRequest) {

        User user = userRepository.findById(baseHeartSaveRequest.userId())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        BaseHeart baseHeart = BaseHeart.builder()
                .user(user)
                .measureTime(baseHeartSaveRequest.measureTime())
                .baseHeart(baseHeartSaveRequest.baseHeart())
                .build();

        BaseHeart savedBaseHeart = baseHeartRepository.save(baseHeart);

        return new BaseHeartResponse(savedBaseHeart.getId(), savedBaseHeart.getBaseHeart(), savedBaseHeart.getMeasureTime());
    }

    public BaseHeartResponse getLatestBaseHeartByUserId(Long userId) {

        if (userRepository.findById(userId).isEmpty()) {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }

        List<BaseHeart> baseHeartList = baseHeartRepository.findByUserIdDescStartTime(userId);

        if (baseHeartList.isEmpty()) throw new CustomException(ErrorCode.BASE_HEART_NOT_FOUND);
        BaseHeart baseHeart = baseHeartList.get(0);

        return new BaseHeartResponse(baseHeart.getId(), baseHeart.getBaseHeart(), baseHeart.getMeasureTime());
    }
}
