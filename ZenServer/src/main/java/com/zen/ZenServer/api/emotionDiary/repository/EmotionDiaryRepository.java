package com.zen.ZenServer.api.emotionDiary.repository;

import com.zen.ZenServer.api.emotionDiary.domain.EmotionDiary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmotionDiaryRepository extends JpaRepository<EmotionDiary, Long> {
}
