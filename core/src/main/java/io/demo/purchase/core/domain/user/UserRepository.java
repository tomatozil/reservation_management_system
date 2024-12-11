package io.demo.purchase.core.domain.user;

import io.demo.purchase.support.RoleType;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository {
    long add(String name, String email, String password);

    User find(long userId);
    User find(String email);
    Optional<Long> find(String name, String email);

    void updateRole(long userId, RoleType to);
}
