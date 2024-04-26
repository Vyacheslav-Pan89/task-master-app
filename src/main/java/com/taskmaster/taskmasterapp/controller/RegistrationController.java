package com.taskmaster.taskmasterapp.controller;

import com.taskmaster.taskmasterapp.model.User;
import com.taskmaster.taskmasterapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registrationForm(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registrationSubmit")
    public String handleRegistration(@Valid User user, BindingResult bindingResult, Model model) {

        if(userService.userNameExist(user)){
            model.addAttribute("messageUserName", "Try different user name");
            return "registration";
        }

        if(userService.emailExist(user)){
            model.addAttribute("messageEmail", "Try different email");
            return "registration";
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "registration";
        }

        userService.add(user);
        return "redirect:/";
    }

}
