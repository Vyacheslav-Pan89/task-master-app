package com.taskmaster.controller;

import com.taskmaster.domain.User;
import com.taskmaster.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user")
    public String userPage(@AuthenticationPrincipal User user) {
        return "user";
    }
}
