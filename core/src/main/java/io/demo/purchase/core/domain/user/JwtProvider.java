package io.demo.purchase.core.domain.user;

import io.demo.purchase.core.domain.error.CoreDomainErrorType;
import io.demo.purchase.support.CustomException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Slf4j
@Component
public class JwtProvider {
    private final SecretKey key;

    protected JwtProvider(@Value("${JWT_SECRET_KEY}") String secretKey) {
        log.info("---- secret key: {} ----", secretKey);

        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);

        SignatureAlgorithm alg = SignatureAlgorithm.HS256;
        this.key = new SecretKeySpec(keyBytes, alg.getJcaName());
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
        log.debug("---- accessToken: {} ----", accessToken);

        try {
            log.info("--- key: {} ---", key);
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
