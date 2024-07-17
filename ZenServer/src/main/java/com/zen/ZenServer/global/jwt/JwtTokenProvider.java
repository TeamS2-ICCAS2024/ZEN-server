package com.zen.ZenServer.global.jwt;

import com.zen.ZenServer.global.auth.domain.Token;
import com.zen.ZenServer.global.auth.repository.TokenRepository;
import com.zen.ZenServer.global.exception.CustomException;
import com.zen.ZenServer.global.response.enums.ErrorCode;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    @Value("${application.security.jwt.key}")
    private String jwtKey;

    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;

    @Value("${application.security.refresh.expiration}")
    private long refreshExpiration;

    private final UserDetailsService userDetailsService;

    private final TokenRepository tokenRepository;

    @PostConstruct
    protected void init() {
        jwtKey = Base64.getEncoder().encodeToString(jwtKey.getBytes());
    }

    public String createAccessToken(UserDetails userDetails) {

        return buildToken(userDetails, jwtExpiration);
    }

    public String createRefreshToken(UserDetails userDetails) {

        return buildToken(userDetails, refreshExpiration);
    }

    public String buildToken(UserDetails userDetails, long expiration) {

        Date now = new Date();

        Claims claims = Jwts.claims()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + expiration));

        return Jwts.builder()
                .setClaims(claims)
                .signWith(getSigningKey())
                .compact();
    }

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(
                jwtKey.getBytes());
    }

    public Authentication getAuthentication(String token) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserPk(token));

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    public String getUserPk(String token) {

        return getBody(token).getSubject();
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring("Bearer ".length());
        } else {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }
    }


    public void validToken(String token) {

        Optional<Token> validToken = tokenRepository.findByToken(token);

        if (token.isEmpty()) {
            throw new CustomException(ErrorCode.EMPTY_TOKEN);
        } else if (validToken.isEmpty()) {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        } else if (validToken.get().isExpired()) {
            throw new CustomException(ErrorCode.EXPIRED_TOKEN);
        }
    }

    private Claims getBody(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}