package io.demo.purchase.core.api.controller.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SigninUserRequest {
    String email;
    String password;

    public SigninUserRequest(String password, String email) {
        this.password = password;
        this.email = email;
    }
}
