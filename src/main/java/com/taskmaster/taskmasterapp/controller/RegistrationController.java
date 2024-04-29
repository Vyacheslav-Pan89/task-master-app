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
import org.springframework.web.bind.annotation.RequestMapping;


//TODO: Controller should have base path. Something like '/registration' from your first method DONE!!!
@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    //TODO: url can be removed. It will take class mapping DONE!!!
    @GetMapping
    public String registrationForm(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    //TODO: please rename it to '/submit'. Full path will be /registration/submit DONE!!!
    @PostMapping("/submit")
    public String handleRegistration(@Valid User user, BindingResult bindingResult, Model model) {


        //TODO: why you are going to DB every time before model fields validation? First should be performed validation on User.class
        // level and afterwards when in passes it should go to DB and check if this login and email not reserved. delete these two if blocks and all will works.
        // You can add to 'spring.jpa.show-sql=true' to config file and you will see in terminal how often you invokes DB DONE!!!

        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "registration";
        }

        //TODO: please create new method in user service to check if unique fields not reserved. If some fields are reserved I as user want to see proper message.
        // Something similar from your first two 'if' DONE!!!

        if (userService.checkNewUserCredentials(user) != null) {
            model.addAttribute("message", userService.checkNewUserCredentials(user));
            return "registration";
        }

        userService.add(user);
        return "redirect:/login";
    }

}
