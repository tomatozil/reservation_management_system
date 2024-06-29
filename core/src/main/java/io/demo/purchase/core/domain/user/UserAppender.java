package io.demo.purchase.core.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserAppender {

    private final UserRepository userRepository;
    private final BcryptEncoder encoder;

    @Autowired
    public UserAppender(UserRepository userRepository, BcryptEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public Integer append(String name, String email, String password) {
        // password encrypt
        String encryptedPassword = encoder.hashPassword(password);
        return userRepository.add(name, email, encryptedPassword);
    }
}
