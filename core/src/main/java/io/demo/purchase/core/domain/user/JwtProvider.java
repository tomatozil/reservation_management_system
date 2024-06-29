package io.demo.purchase.core.domain.user;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class JwtProvider {
    private final SecretKey key;

    protected JwtProvider() {
        this.key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    public String generate(Integer userId) {
        return Jwts.builder()
                .setSubject(userId.toString())
                .setIssuedAt(Date.from(LocalDateTime.now().atZone(ZoneId.of("Asia/Seoul")).toInstant()))
                .setExpiration(Date.from(LocalDateTime.now().plusHours(72).atZone(ZoneId.of("Asia/Seoul")).toInstant()))
                .signWith(key)
                .compact();
    }

    public SecretKey getKey() {
        return key;
    }
}
