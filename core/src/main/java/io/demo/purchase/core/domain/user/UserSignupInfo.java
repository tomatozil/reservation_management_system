package io.demo.purchase.core.domain.user;

import lombok.Getter;

@Getter
public class UserSignupInfo {
    String name;
    String email;
    String password;

    public UserSignupInfo(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
