package com.taskmaster.authentication.controller;

import com.taskmaster.authentication.model.User;
import com.taskmaster.authentication.model.UserDTO;
import com.taskmaster.authentication.service.EmailService;
import com.taskmaster.authentication.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private final UserService userService;
    private final EmailService emailService;


    @Autowired
    public RegistrationController(UserService userService, EmailService emailService) {
        this.userService = userService;
        this.emailService = emailService;

    }

    @GetMapping
    public String registrationForm(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "registration";
    }

    @PostMapping
    public String handleRegistration(@Valid UserDTO userDTO, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "registration";
        }

        User user = User.mapToUser(userDTO);

        String validationMessage = userService.checkNewUserCredentials(user);

        if (validationMessage != null) {
            model.addAttribute("message", validationMessage);
            return "registration";
        }

        userService.add(user);
        emailService.sendEmail(user);
        return "redirect:/registration/completion";
    }

    @GetMapping("/completion")
    public String viewRegistrationCompletion() {
        return "registration-completion";
    }


    @GetMapping("/activation/{token}")
    public String accountActivation(@PathVariable String token) {
        userService.activateUser(token);
        return "activated";
    }

}
