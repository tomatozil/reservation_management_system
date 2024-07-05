package io.demo.purchase.core.api.controller;

import io.demo.purchase.core.api.controller.request.SigninUserRequest;
import io.demo.purchase.core.api.controller.request.SignupUserRequest;
import io.demo.purchase.core.api.controller.response.AppendUserResponse;
import io.demo.purchase.core.api.controller.response.UserTokenInfo;
import io.demo.purchase.core.domain.error.CoreDomainErrorType;
import io.demo.purchase.core.domain.user.BcryptEncoder;
import io.demo.purchase.core.domain.user.User;
import io.demo.purchase.core.domain.user.UserService;
import io.demo.purchase.support.CustomException;
import io.demo.purchase.support.argumentresolver.AuthorizedUser;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService userService;
    private final BcryptEncoder bcryptEncoder;

    @Autowired
    public UserController(UserService userService, BcryptEncoder bcryptEncoder) {
        this.userService = userService;
        this.bcryptEncoder = bcryptEncoder;
    }

    @PostMapping("/user/signup")
    public AppendUserResponse signup(@RequestBody SignupUserRequest request, HttpServletResponse response) {
        UserTokenInfo userTokenInfo = userService.newUser(request.toUserSignupInfo());
        // 쿠키에 access token 담기
        response.addCookie(new Cookie("accessToken", userTokenInfo.getAccessToken()));

        return userTokenInfo.toResponse();
    }

    @GetMapping("/user/signin")
    public void signin(@AuthorizedUser User user, @RequestBody SigninUserRequest request) {
        // 비밀번호 확인하기
        if (!bcryptEncoder.isMatch(request.getPassword(), user.getPassword()))
            throw new CustomException(CoreDomainErrorType.BAD_REQUEST_DATA, "비밀번호가 일치하지 않습니다");
    }
}
