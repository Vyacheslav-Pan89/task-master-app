package com.taskmaster.controller;

import com.taskmaster.domain.CustomUser;
import com.taskmaster.security.CustomUserDetailsService;
import com.taskmaster.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final CustomUserDetailsService userDetailsService;
    private final UserService userService;

    @ModelAttribute("user")
    public CustomUser getAuthenticatedUser() {
        String username = userDetailsService.getAuthenticatedUsername();
        return userService.findUserByUserName(username);
    }

    @GetMapping("/user")
    public String userPage() {
        return "user";
    }
}
