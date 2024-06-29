package io.demo.purchase.core.api.controller;

import io.demo.purchase.core.api.controller.request.AppendUserRequest;
import io.demo.purchase.core.domain.user.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signUp")
    public void signup(@RequestBody AppendUserRequest request, HttpServletResponse response) {
        String accessToken = userService.newUser(request.toUserSignupInfo());
        // 쿠키에 access token 담기
        response.addCookie(new Cookie("accessToken", accessToken));
    }
}
