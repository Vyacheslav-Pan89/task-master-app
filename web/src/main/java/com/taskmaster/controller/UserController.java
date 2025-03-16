package com.taskmaster.controller;

import com.taskmaster.domain.User;
import com.taskmaster.security.UserDetailsService;
import com.taskmaster.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserDetailsService userDetailsService;
    private final UserService userService;

    @ModelAttribute("user")
    public User getAuthenticatedUser() {
        String username = userDetailsService.getAuthenticatedUsername();
        return userService.findUserByUserName(username);
    }

    @GetMapping("/user")
    public String userPage() {
        return "user";
    }
}
