package com.taskmaster.controller;

import com.taskmaster.domain.CustomUser;
import com.taskmaster.model.UserModel;
import com.taskmaster.service.EmailService;
import com.taskmaster.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;
    private final EmailService emailService;
    private final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);

    @GetMapping
    public String registrationForm(Model model) {
        model.addAttribute("userDTO", new UserModel());
        return "registration";
    }

    //TODO: 500 code. You forgot to add something here))
    @PostMapping
    public String handleRegistration(@Valid UserModel userModel,
                                     BindingResult bindingResult, Model model) {
        LOGGER.info(userModel.toString());
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "registration";
        }

        //TODO: no logic should be in controller. Please move all logic to service layer
        CustomUser user = CustomUser.mapToUser(userModel);

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
