package io.demo.purchase.core.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private final UserAppender userAppender;

    @Autowired
    public UserService(UserAppender userAppender) {
        this.userAppender = userAppender;
    }

    public String newUser(UserSignUpInfo userSignUpInfo) {
        return userAppender.append(userSignUpInfo);
    }
}
