package io.demo.purchase.core.domain.user;

import io.demo.purchase.support.RoleType;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {
    long add(String name, String email, String password);

    User find(long userId);
    User find(String email);
    User find(String name, String email);

    void updateRole(long userId, RoleType to);
}
