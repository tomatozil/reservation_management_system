package io.demo.purchase.core.api.controller.request;

import io.demo.purchase.core.domain.user.UserSignUpInfo;
import io.demo.purchase.core.support.CustomException;
import lombok.Setter;

import static io.demo.purchase.core.domain.user.error.CoreDomainErrorType.BAD_REQUEST_DATA;

@Setter
public class AppendUserRequest {
    private String name;
    private String password;
    private String email;

    public UserSignUpInfo toUserAppender() {
        // name, password, email 검증 및 변환
        if (name.length() > 10)
            throw new CustomException(BAD_REQUEST_DATA);
        return new UserSignUpInfo(
                name,
                password,
                email
        );
    }
}
