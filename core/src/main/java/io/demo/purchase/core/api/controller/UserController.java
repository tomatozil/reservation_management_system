package io.demo.purchase.core.api.controller;

import io.demo.purchase.core.api.controller.request.SigninUserRequest;
import io.demo.purchase.core.api.controller.request.SignupUserRequest;
import io.demo.purchase.core.api.controller.response.AppendUserResponse;
import io.demo.purchase.core.domain.user.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService userService;
    private final AuthProvider authProvider;

    @Autowired
    public UserController(UserService userService, AuthProvider authProvider) {
        this.userService = userService;
        this.authProvider = authProvider;
    }

    @PostMapping("/user/signup")
    public AppendUserResponse signup(@RequestBody SignupUserRequest request) {
        long userId = userService.add(request.toUserSignupInfo());

        return new AppendUserResponse(userId);
    }

    @GetMapping("/user/signin")
    public void signin(@RequestBody SigninUserRequest request, HttpServletResponse response) {
        String accessToken = authProvider.checkAvailability(request.getEmail(), request.getPassword());
        // 쿠키에 access token 담기
        response.addCookie(new Cookie("accessToken", accessToken));
    }
}
