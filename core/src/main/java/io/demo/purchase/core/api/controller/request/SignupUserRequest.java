package io.demo.purchase.core.api.controller.request;

import io.demo.purchase.core.domain.user.UserSignupInfo;
import io.demo.purchase.support.Regex;
import io.demo.purchase.support.CustomException;
import io.demo.purchase.core.domain.error.CoreDomainErrorType;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Setter
@Slf4j
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
        if (name.length() > 20)
            throw new CustomException(CoreDomainErrorType.BAD_REQUEST_DATA, "이름은 20자 이하로 작성해주세요");
        if (!Regex.PASSWORD_REGEX.isMatched(password))
            throw new CustomException(CoreDomainErrorType.BAD_REQUEST_DATA, "비밀번호는 6자 이상 작성해주세요");
        if (!Regex.EMAIL_REGEX.isMatched(email))
            throw new CustomException(CoreDomainErrorType.BAD_REQUEST_DATA, "이메일 형식에 맞게 작성해주세요");


        return new UserSignupInfo(
                name,
                email,
                password
        );
    }
}
