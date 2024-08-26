package io.demo.purchase.core.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserWriter {

    private final UserRepository userRepository;
    private final AuthProvider authProvider;

    @Autowired
    public UserWriter(UserRepository userRepository, AuthProvider authProvider) {
        this.userRepository = userRepository;
        this.authProvider = authProvider;
    }

    public long append(String name, String email, String password) {
        // 비밀번호 암호화
        String encryptedPassword = authProvider.encryptPassword(password);
        // 저장하기
        return userRepository.add(name, email, encryptedPassword);
    }
}
