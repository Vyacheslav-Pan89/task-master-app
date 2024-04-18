package com.taskmaster.taskmasterapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home"; // This assumes that your home.html file is in src/main/resources/templates directory
    }
}
