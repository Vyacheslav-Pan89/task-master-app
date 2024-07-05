package com.taskmaster.authentication.controller;

import com.taskmaster.authentication.model.LoginRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return "home";
    }

}
