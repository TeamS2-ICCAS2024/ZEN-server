package com.zen.ZenServer.api.baseHeart.repository;

import com.zen.ZenServer.api.baseHeart.domain.BaseHeart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface BaseHeartRepository extends JpaRepository<BaseHeart, Long> {

    @Query("SELECT b FROM BaseHeart b WHERE b.user.id = :userId ORDER BY b.measureTime DESC, b.id DESC")
    List<BaseHeart> findByUserIdDescStartTime(@Param("userId") Long userId);
}
