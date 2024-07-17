package com.zen.ZenServer.global.auth.service;

import com.zen.ZenServer.api.user.domain.User;
import com.zen.ZenServer.api.user.repository.UserRepository;
import com.zen.ZenServer.global.auth.TokenType;
import com.zen.ZenServer.global.auth.domain.Token;
import com.zen.ZenServer.global.auth.dto.request.AuthenticationRequest;
import com.zen.ZenServer.global.auth.dto.request.RegisterRequest;
import com.zen.ZenServer.global.auth.dto.response.AuthenticationResponse;
import com.zen.ZenServer.global.auth.repository.TokenRepository;
import com.zen.ZenServer.global.exception.CustomException;
import com.zen.ZenServer.global.jwt.JwtTokenProvider;
import com.zen.ZenServer.global.response.enums.ErrorCode;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthenticationService  {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationResponse register(RegisterRequest registerRequest) {

        if (userRepository.findByEmail(registerRequest.email()).isPresent()) {
            throw new CustomException(ErrorCode.DUPLICATED_EMAIL_USER);
        }

        User user = User.builder()
                .nickname(registerRequest.nickname())
                .email(registerRequest.email())
                .password(passwordEncoder.encode(registerRequest.password()))
                .build();

        userRepository.save(user);

        String accessToken = jwtTokenProvider.createAccessToken(user);
        String refreshToken = jwtTokenProvider.createRefreshToken(user);

        saveUserToken(user, accessToken, TokenType.ACCESS_TOKEN);
        saveUserToken(user, refreshToken, TokenType.REFRESH_TOKEN);

        return new AuthenticationResponse(user.getId(), accessToken, refreshToken);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {

        User user = userRepository.findByEmail(authenticationRequest.email())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.email(),
                        authenticationRequest.password()
                )
        );

        String accessToken = jwtTokenProvider.createAccessToken(user);
        String refreshToken = jwtTokenProvider.createRefreshToken(user);

        saveUserToken(user, accessToken, TokenType.ACCESS_TOKEN);
        saveUserToken(user, refreshToken, TokenType.REFRESH_TOKEN);

        return new AuthenticationResponse(user.getId(), accessToken, refreshToken);
    }

    private void saveUserToken(User user, String jwtToken, TokenType tokenType) {

        Token token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(tokenType)
                .isExpired(false)
                .build();

        tokenRepository.save(token);
    }

    public void revokeAllUserTokens(User user) {

        List<Token> validTokens = tokenRepository.findAllValidTokenByUser(user);

        if (!validTokens.isEmpty()) {
            validTokens.forEach(token -> {
                token.setExpired(true);
            });
            tokenRepository.saveAll(validTokens);
        }
    }
}
