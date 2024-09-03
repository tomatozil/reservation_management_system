package io.demo.purchase.core.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserReader {
    private final UserRepository userRepository;

    @Autowired
    public UserReader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(long userId) {
        return userRepository.find(userId);
    }

    public Optional<Long> findExist(String name, String email) {
        return userRepository.find(name, email);
    }

    public User findByEmail(String email) {
        return userRepository.find(email);
    }
}
