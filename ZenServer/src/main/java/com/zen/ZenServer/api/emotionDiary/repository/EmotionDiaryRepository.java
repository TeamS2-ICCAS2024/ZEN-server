package com.zen.ZenServer.api.emotionDiary.repository;

import com.zen.ZenServer.api.emotionDiary.domain.EmotionDiary;
import com.zen.ZenServer.global.exception.CustomException;
import com.zen.ZenServer.global.response.enums.ErrorCode;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmotionDiaryRepository extends JpaRepository<EmotionDiary, Long> {
    List<EmotionDiary> findByUserId(Long userId);

    List<EmotionDiary> findTop4ByEmotionStateAndUserId(String EmotionState, Long userId);

    default EmotionDiary findByIdOrThrow(Long id) {
        return findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.EMOTIONDIARY_NOT_FOUND));
    }
}
