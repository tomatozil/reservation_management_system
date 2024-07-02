package io.demo.purchase.core.domain.user;

import io.demo.purchase.core.api.controller.response.UserTokenInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserAppender userAppender;

    @Autowired
    public UserService(UserAppender userAppender, JwtProvider jwtProvider) {
        this.userAppender = userAppender;
    }

    public UserTokenInfo newUser(UserSignupInfo userSignUpInfo) {
        return userAppender.append(userSignUpInfo.name, userSignUpInfo.email, userSignUpInfo.password);
    }
}
