package com.gabriel.reservaareacomum.infra.providers;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtEncoder {
    private final Long EXPIRATION_TIME;
    private final SecretKey key;

    public JwtEncoder(@Value("${SECRET_KEY}") String SECRET_KEY, @Value("${EXPIRATION_TIME}") Long EXPIRATION_TIME) {
        this.key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
        this.EXPIRATION_TIME = EXPIRATION_TIME;
    }

    public String generate(String value, Map<String, Boolean> permissions) {
        return Jwts.builder()
                .setClaims(permissions)
                .setSubject(value)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    public Map<String, Boolean> getClaims(String token) {
        Claims body = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        Map<String, Boolean> permissions = new HashMap<>();
        permissions.put("isAdm", body.get("isAdm", Boolean.class));
        permissions.put("isUser", body.get("isUser", Boolean.class));

        return permissions;
    }
}
