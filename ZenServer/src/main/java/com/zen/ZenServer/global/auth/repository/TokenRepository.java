package com.zen.ZenServer.global.auth.repository;

import com.zen.ZenServer.api.user.domain.User;
import com.zen.ZenServer.global.auth.domain.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {

    List<Token> findAllValidTokenByUser(User user);

    Optional<Token> findByToken(String token);
}