package io.demo.purchase.core.api.controller;

import io.demo.purchase.core.api.controller.request.AppendUserRequest;
import io.demo.purchase.core.domain.user.UserService;
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
    public void signUp(@RequestBody AppendUserRequest request) {
        // 생성하고 uuid 받기
        String uuid = userService.newUser(request.toUserAppender());
        // 쿠키에 담기? 응답에 담기?
    }
}
