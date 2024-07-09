package io.demo.purchase.core.domain.user;

import io.demo.purchase.core.domain.error.CoreDomainErrorType;
import io.demo.purchase.support.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserReader userReader;
    private final UserAppender userAppender;

    @Autowired
    public UserService(UserReader userReader, UserAppender userAppender) {
        this.userReader = userReader;
        this.userAppender = userAppender;
    }

    public long add(UserSignupInfo userSignUpInfo) {
        try {
            User user = userReader.findExist(userSignUpInfo.name, userSignUpInfo.email);

        } catch (CustomException e) {
            if (e.getStatusCode() == HttpStatus.BAD_REQUEST.value())
                return userAppender.append(userSignUpInfo.name, userSignUpInfo.email, userSignUpInfo.password);
            else
                throw e;
        }
        throw new CustomException(CoreDomainErrorType.REQUEST_FAILED, "회원가입을 이미 완료한 유저입니다");
    }
}
