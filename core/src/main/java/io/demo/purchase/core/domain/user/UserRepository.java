package io.demo.purchase.core.domain.user;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {
    Integer add(String name, String email, String password);
}
