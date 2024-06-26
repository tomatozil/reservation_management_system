package io.demo.purchase.core.domain.user;

import lombok.Getter;

@Getter
public class UserSignUpInfo {
    private String name;
    private String password;
    private String email;

    public UserSignUpInfo(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }
}
