package io.demo.purchase.core.domain.user;

import io.demo.purchase.storage.AlertAdminCheckException;
import io.demo.purchase.support.exception.CoreDomainErrorType;
import io.demo.purchase.support.RoleType;
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

    public void updateRole(long userId, RoleType to) {
        User user = userRepository.find(userId);

        if (user.role == to)
            throw new AlertAdminCheckException(CoreDomainErrorType.REQUEST_FAILED, "역할 변경 요청이 실패했습니다");

        userRepository.updateRole(userId, to);
    }
}
