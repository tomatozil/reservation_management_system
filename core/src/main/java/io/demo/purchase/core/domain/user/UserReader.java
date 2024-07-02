package io.demo.purchase.core.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserReader {
    private final UserRepository userRepository;

    @Autowired
    public UserReader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User find(long userId) {
        return userRepository.find(userId);
    }
}
