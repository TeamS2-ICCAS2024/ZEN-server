package com.zen.ZenServer.global.auth.domain;

import com.zen.ZenServer.api.user.domain.User;
import com.zen.ZenServer.global.auth.TokenType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String token;

    private TokenType tokenType;

    private boolean isExpired;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}