package com.taskmaster.controller;

import com.taskmaster.domain.CustomUser;
import com.taskmaster.security.CustomUserDetailsService;
import com.taskmaster.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    private final CustomUserDetailsService userDetailsService;
    private final UserService userService;

    @ModelAttribute("user")
    public CustomUser authenticatedUser() {
        String username = userDetailsService.getAuthenticatedUsername();
        LOGGER.info("Authenticated user: {}", username);
        return userService.findUserByUserName(username);
    }

    @GetMapping("/home")
    public String home() {
        LOGGER.info("Entering home controller!");
        return "home";
    }
    @GetMapping("/logout")
    public String logout() {
        LOGGER.info("Logging out!");
        return "redirect:/login";
    }
}
