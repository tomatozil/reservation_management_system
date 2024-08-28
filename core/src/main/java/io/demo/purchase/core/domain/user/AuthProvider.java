package io.demo.purchase.core.domain.user;

import io.demo.purchase.core.AlertUserRetryException;
import io.demo.purchase.support.exception.CoreDomainErrorType;
import org.springframework.stereotype.Component;

@Component
public class AuthProvider {
    private final UserReader userReader;
    private final BcryptEncoder bcryptEncoder;
    private final JwtProvider jwtProvider;

    public AuthProvider(UserReader userReader, BcryptEncoder bcryptEncoder, JwtProvider jwtProvider) {
        this.userReader = userReader;
        this.bcryptEncoder = bcryptEncoder;
        this.jwtProvider = jwtProvider;
    }

    public String encryptPassword(String input) {
        return bcryptEncoder.hashPassword(input);
    }

    public String checkAvailability(String email, String inputPassword) {
        User user = userReader.findByEmail(email);

        // 비밀번호 확인하기
        if (!bcryptEncoder.isMatch(inputPassword, user.getPassword()))
            throw new AlertUserRetryException(CoreDomainErrorType.BAD_REQUEST_DATA, "비밀번호가 일치하지 않습니다");

        // access token 만들기
        return this.generateAccessToken(user.getId());
    }

    private String generateAccessToken(long userId) {
        // access token 만들기
        return jwtProvider.generate(userId);
    }
}
