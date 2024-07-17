package com.zen.ZenServer.api.baseHeart.domain;

import com.zen.ZenServer.api.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BaseHeart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int baseHeart;

    private LocalDateTime measureTime;

    @ManyToOne
    private User user;
}
