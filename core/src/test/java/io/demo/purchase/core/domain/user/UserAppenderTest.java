package io.demo.purchase.core.domain.user;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.mindrot.jbcrypt.BCrypt;

import io.demo.purchase.core.api.controller.response.UserTokenInfo;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@SpringBootTest
class UserAppenderTest {

//    @Autowired
//    UserAppender userAppender;


    UserRepository userRepositoryMock = new UserRepository() {
        @Override
        public long add(String name, String email, String password) {
            return 1L;
        }

        @Override
        public User find(long userId) {
            return null;
        }

    };

    BcryptEncoder bcryptEncoderMock = new BcryptEncoder() {
        @Override
        public String hashPassword(String password) {
            System.out.println(BCrypt.hashpw(password, BCrypt.gensalt()));
            return "encryptedPassword";
        }
    };
    JwtProvider jwtProviderMock = new JwtProvider() {
        @Override
        public String generate(Long userId) {
            SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
            System.out.println(
                    Jwts.builder()
                            .setSubject(userId.toString())
                            .setIssuedAt(Date.from(LocalDateTime.now().atZone(ZoneId.of("Asia/Seoul")).toInstant()))
                            .setExpiration(Date.from(LocalDateTime.now().plusHours(72).atZone(ZoneId.of("Asia/Seoul")).toInstant()))
                            .signWith(key)
                            .compact()
            );
            return "accessToken";
        }
    };

    @Test
    @DisplayName("유저 추가시 id와 토큰을 담은 UserTokenInfo 객체가 잘 만들어지는지 확인")
    @Transactional
    void appendTest() {
        UserAppender userAppender = new UserAppender(
                userRepositoryMock,
                bcryptEncoderMock,
                jwtProviderMock
        );

        UserTokenInfo userTokenInfo = userAppender.append("jiyun", "email", "password");

        assertThat(userTokenInfo.getUserId()).isEqualTo(1L);
        assertThat(userTokenInfo.getAccessToken()).isEqualTo("accessToken");
    }
}