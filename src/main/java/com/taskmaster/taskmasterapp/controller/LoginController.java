package com.taskmaster.taskmasterapp.controller;

import com.taskmaster.taskmasterapp.model.LoginRequest;
import com.taskmaster.taskmasterapp.model.User;
import com.taskmaster.taskmasterapp.security.PasswordHashingUtil;
import com.taskmaster.taskmasterapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/", "/login"})
public class LoginController {

    private final UserService userService;
    private final PasswordHashingUtil passwordHashingUtil;

    @Autowired
    public LoginController(UserService userService, PasswordHashingUtil passwordHashingUtil) {
        this.userService = userService;
        this.passwordHashingUtil = passwordHashingUtil;
    }

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
        if (user == null) {
            model.addAttribute("message", "Wrong login or password");
            return "login";
        }

        // TODO: You have made 'password' transient, so it won't be persistent in DB, hence when you retrieve data from DB in line 44 null will be mapped to 'password' -> NPE will be thrown. Solution can be found in comments in User.class
        if (!user.getHashedPassword().equals(passwordHashingUtil.hashPassword(loginRequest.getPassword()))) {
            model.addAttribute("message", "Wrong login or password");
            return "login";
        }


        model.addAttribute("user", userService.findUserByUserName(loginRequest.getUserName()));
        return "redirect:/home";
    }
}
