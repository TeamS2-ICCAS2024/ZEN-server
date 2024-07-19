package com.zen.ZenServer.api.emotionDiary.repository;

import com.zen.ZenServer.api.emotionDiary.domain.EmotionDiary;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmotionDiaryRepository extends JpaRepository<EmotionDiary, Long> {
    List<EmotionDiary> findByUserId(Long userId);
}
