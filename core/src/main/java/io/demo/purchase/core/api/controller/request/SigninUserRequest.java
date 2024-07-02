package io.demo.purchase.core.api.controller.request;

import lombok.Setter;

@Setter
public class SigninUserRequest {
    String email;
    String password;

    public SigninUserRequest(String password, String email) {
        this.password = password;
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
}
