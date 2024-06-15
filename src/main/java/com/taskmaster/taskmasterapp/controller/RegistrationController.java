package com.taskmaster.taskmasterapp.controller;

import com.taskmaster.taskmasterapp.model.User;
import com.taskmaster.taskmasterapp.model.UserDTO;
import com.taskmaster.taskmasterapp.service.EmailService;
import com.taskmaster.taskmasterapp.service.UserBuilderService;
import com.taskmaster.taskmasterapp.service.UserService;
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
    private final UserBuilderService userBuilderService;

    @Autowired
    public RegistrationController(UserService userService, EmailService emailService, UserBuilderService userBuilderService) {
        this.userService = userService;
        this.emailService = emailService;
        this.userBuilderService = userBuilderService;
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


        //TODO: please create separate mapping method with all builder logic from dto to user DONE!!!
        User user = userBuilderService.buildUser(userDTO);

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
