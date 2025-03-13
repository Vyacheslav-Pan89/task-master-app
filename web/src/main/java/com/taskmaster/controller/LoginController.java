package com.taskmaster.controller;

import com.taskmaster.domain.User;
import com.taskmaster.model.LoginRequest;
import com.taskmaster.security.PasswordHashingUtil;
import com.taskmaster.service.LoginService;
import com.taskmaster.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/", "/login"})
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;
    private final PasswordHashingUtil passwordHashingUtil;
    private final LoginService loginService;

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

        if (!loginService.isValidationSucceed(user, loginRequest, model)) {
            return "login";
        }

        model.addAttribute("user", user);
        return "redirect:/home";
    }
}
