package com.taskmaster.controller;

import com.taskmaster.model.LoginRequest;
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

    @ModelAttribute("loginRequest")
    public LoginRequest getLoginRequest() {
        return new LoginRequest();
    }

    @GetMapping
    public String login() {
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
        return "redirect:/home";
    }
}
