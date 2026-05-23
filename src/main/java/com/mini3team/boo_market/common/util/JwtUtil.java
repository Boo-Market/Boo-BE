package com.mini3team.boo_market.common.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {
    private final SecretKey key = Keys.hmacShaKeyFor(
            "boo-market-secret-key-must-be-longer-than-32-bytes".getBytes(StandardCharsets.UTF_8)
    );

    public String createToken(Long userId) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + 1000L * 60 * 60 * 24);
        return Jwts.builder()
                .subject(String.valueOf(userId))
                .issuedAt(now)
                .expiration(expiration)
                .signWith(key)
                .compact();
    }

    public Long getUserId(String token) {
        return Long.valueOf(Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject());
    }
}
