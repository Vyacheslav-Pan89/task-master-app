package com.taskmaster.taskmasterapp.controller;

import com.taskmaster.taskmasterapp.model.LoginRequest;
import com.taskmaster.taskmasterapp.model.User;
import com.taskmaster.taskmasterapp.security.PasswordHashingUtil;
import com.taskmaster.taskmasterapp.service.LoginService;
import com.taskmaster.taskmasterapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/", "/login"})
public class LoginController {

    private final UserService userService;
    private final PasswordHashingUtil passwordHashingUtil;
    private final LoginService loginService;

    @Autowired
    public LoginController(UserService userService, PasswordHashingUtil passwordHashingUtil, LoginService loginService) {
        this.userService = userService;
        this.passwordHashingUtil = passwordHashingUtil;
        this.loginService = loginService;
    }

    @GetMapping
    public String login(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return "login";
    }

    @PostMapping
    public String handleLogin(@Valid @ModelAttribute("loginRequest") LoginRequest loginRequest,
                              BindingResult bindingResult,
                              Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "login";
        }

        User user = userService.findUserByName(loginRequest.getUserName());

        //TODO: to much service logic in controller. Please extract all these ifs to separate method, with validation naming DONE?

        String message = loginService.loginPermission(user, loginRequest);
        if (!message.equals("OK")) {
            model.addAttribute("message", message);
            return "login";
        }

        //TODO: duplicated method with line 47 DONE?
        model.addAttribute("user", user);
        return "redirect:/home";
    }
}
