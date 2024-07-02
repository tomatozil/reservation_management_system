package io.demo.purchase.core.api.controller.request;

import io.demo.purchase.core.domain.user.UserSignupInfo;
import io.demo.purchase.support.Constants;
import io.demo.purchase.support.CustomException;
import io.demo.purchase.core.domain.error.CoreDomainErrorType;
import lombok.Setter;

@Setter
public class SignupUserRequest {
    String name;
    String email;
    String password;

    public SignupUserRequest(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public UserSignupInfo toUserSignupInfo() {
        // name, password, email 검증
        if (name.length() > 10
                || !Constants.PASSWORD_REGEX.isMatched(password)
                || !Constants.EMAIL_REGEX.isMatched(email))
            throw new CustomException(CoreDomainErrorType.BAD_REQUEST_DATA);

        return new UserSignupInfo(
                name,
                email,
                password
        );
    }
}
