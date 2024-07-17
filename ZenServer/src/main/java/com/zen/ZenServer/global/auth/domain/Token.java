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
    public Long id;

    @Column(unique = true)
    public String token;

    public TokenType tokenType;

    public boolean isExpired;

    @ManyToOne(fetch = FetchType.LAZY)
    public User user;
}