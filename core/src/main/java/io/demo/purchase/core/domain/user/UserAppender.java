package io.demo.purchase.core.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserAppender {

    private final UserRepository userRepository;

    @Autowired
    public UserAppender(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String append(UserSignUpInfo userSignUpInfo) { return ""; }
}
