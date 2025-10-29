package com.example.portfoliospring1.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
    @Value("${jwt.secret}") private String secret;
    private long expiresMin = 15;

    private Key key(){
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(Long userId, String nickname, String email){
        Instant now = Instant.now();

        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .claim("nickname",email)
                .setIssuedAt(Date.from(now))
                .setExpiration(
                        Date.from(
                                now.plus(Duration.ofMinutes((expiresMin)))
                        )
                ).signWith(key(),SignatureAlgorithm.HS256)
                .compact();
    }
}
