package com.taskmaster.taskmasterapp.controller;

import com.taskmaster.taskmasterapp.security.LoginRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return "home";
    }
}
