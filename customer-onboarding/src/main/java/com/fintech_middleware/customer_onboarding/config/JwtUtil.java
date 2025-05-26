package com.fintech_middleware.customer_onboarding.config;

import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;


import java.util.Date;
@Component
public class JwtUtil {
    private final String secret;

    private final long expirationMillis;

    public JwtUtil(@Value("${jwt.secret}") String secret,
                   @Value("${jwt.expiration}") long expirationMillis) {
        this.secret = secret;
        this.expirationMillis = expirationMillis;
    }

    public String generateToken(String username) {
        // Use expirationMillis internally
        long now = System.currentTimeMillis();
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + expirationMillis))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
}
