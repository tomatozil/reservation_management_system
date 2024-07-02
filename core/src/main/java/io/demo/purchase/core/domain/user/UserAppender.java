package io.demo.purchase.core.domain.user;

import io.demo.purchase.core.api.controller.response.UserTokenInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserAppender {

    private final UserRepository userRepository;
    private final BcryptEncoder bcryptEncoder;
    private final JwtProvider jwtProvider;

    @Autowired
    public UserAppender(UserRepository userRepository, BcryptEncoder bcryptEncoder, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.bcryptEncoder = bcryptEncoder;
        this.jwtProvider = jwtProvider;
    }

    public UserTokenInfo append(String name, String email, String password) {
        // 비밀번호 암호화
        String encryptedPassword = bcryptEncoder.hashPassword(password);
        // 저장하기
        long userId = userRepository.add(name, email, encryptedPassword);
        // access token 만들기
        String accessToken = jwtProvider.generate(userId);

        return new UserTokenInfo(userId, accessToken);
    }
}
