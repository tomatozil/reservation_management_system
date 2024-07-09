package io.demo.purchase.core.domain.user;

import lombok.Getter;

@Getter
public class User {
    long id;
    String name;
    String email;
    private String password;

    public User(Long id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
