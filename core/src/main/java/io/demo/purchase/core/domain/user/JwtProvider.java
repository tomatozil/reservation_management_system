package io.demo.purchase.core.domain.user;

import io.demo.purchase.core.domain.error.CoreDomainErrorType;
import io.demo.purchase.support.CustomException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Component
public class JwtProvider {
    private final SecretKey key;

    protected JwtProvider() {
        this.key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    public String generate(Long userId) {
        return Jwts.builder()
                .setSubject(userId.toString())
                .setIssuedAt(Date.from(LocalDateTime.now().atZone(ZoneId.of("Asia/Seoul")).toInstant()))
                .setExpiration(Date.from(LocalDateTime.now().plusHours(72).atZone(ZoneId.of("Asia/Seoul")).toInstant()))
                .signWith(key)
                .compact();
    }

    public Long verifyToken(String accessToken) {
        try {
            String subject = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(accessToken)
                    .getBody()
                    .getSubject();
            return Long.parseLong(subject);

        } catch (Exception e) {
            throw new CustomException(CoreDomainErrorType.UNAUTHORIZED, "token 검증에 실패했습니다");
        }
    }

    public SecretKey getKey() {
        return key;
    }
}
