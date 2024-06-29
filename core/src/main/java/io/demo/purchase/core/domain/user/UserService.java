package io.demo.purchase.core.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserAppender userAppender;
    private final JwtProvider jwtProvider;

    @Autowired
    public UserService(UserAppender userAppender, JwtProvider jwtProvider) {
        this.userAppender = userAppender;
        this.jwtProvider = jwtProvider;
    }

    public String newUser(UserSignupInfo userSignUpInfo) {
        Integer userId = userAppender.append(userSignUpInfo.name, userSignUpInfo.email, userSignUpInfo.password);
        return jwtProvider.generate(userId);
    }
}
