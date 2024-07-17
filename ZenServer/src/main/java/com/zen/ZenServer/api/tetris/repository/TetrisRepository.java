package com.zen.ZenServer.api.tetris.repository;

import com.zen.ZenServer.api.tetris.domain.TetrisResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TetrisRepository extends JpaRepository<TetrisResult, Long> {
    @Query("SELECT tr FROM TetrisResult tr WHERE YEAR(tr.gameStartTime) = :year AND MONTH(tr.gameStartTime) = :month AND tr.user.id = :userId ORDER BY tr.gameStartTime DESC")
    List<TetrisResult> findByYearAndMonth(@Param("year") Integer year, @Param("month") Integer month, @Param("userId") Long userId);

    @Query("SELECT tr FROM TetrisResult tr WHERE tr.user.id = :userId AND tr.id = :gameId")
    Optional<TetrisResult> findByUserIdAndGameId(@Param("userId") Long userId, @Param("gameId") Long gameId);
}
