package io.demo.purchase.core.domain.user;

import io.demo.purchase.support.RoleType;
import lombok.Getter;

@Getter
public class User {
    long id;
    RoleType role;
    String name;
    String email;
    private String password;

    public User(Long id, RoleType role, String name, String email, String password) {
        this.id = id;
        this.role = role;
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
