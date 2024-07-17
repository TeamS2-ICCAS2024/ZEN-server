package com.zen.ZenServer.api.tetris.domain;

import com.zen.ZenServer.api.baseHeart.domain.BaseHeart;
import com.zen.ZenServer.api.user.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TetrisResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int score;
    private int level;
    private int lives;

    @ManyToOne(fetch = FetchType.LAZY)
    private BaseHeart baseHeartRate;

    @ElementCollection
    private List<Integer> heartRateList;

    private LocalDateTime gameStartTime;
    private LocalDateTime gameEndTime;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
