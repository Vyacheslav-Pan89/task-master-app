package com.taskmaster.taskmasterapp.controller;

import com.taskmaster.taskmasterapp.model.User;
import com.taskmaster.taskmasterapp.security.LoginRequest;
import com.taskmaster.taskmasterapp.service.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @ModelAttribute("loginRequest") LoginRequest loginRequest, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Access Denied");
        }

        User user = userService.findUserByName(loginRequest.getUserName());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .header("Location", "/home")
                    .body("Invalid Username or Password");
        }

        if (!user.getPassword().equals(loginRequest.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .header("Location", "/home")
                    .body("Invalid Username or Password");
        }

        return ResponseEntity.ok("Login Successful");
    }
}
