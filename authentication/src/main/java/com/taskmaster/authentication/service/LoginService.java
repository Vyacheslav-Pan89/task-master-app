package com.taskmaster.authentication.service;

import com.taskmaster.authentication.model.LoginRequest;
import com.taskmaster.authentication.model.Status;
import com.taskmaster.authentication.model.User;
import com.taskmaster.authentication.security.PasswordHashingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class LoginService {

    private final PasswordHashingUtil passwordHashingUtil;

    @Autowired
    public LoginService(PasswordHashingUtil passwordHashingUtil) {
        this.passwordHashingUtil = passwordHashingUtil;
    }

    public boolean isValidationSucceed(User user, LoginRequest loginRequest, Model model) {

        if (user == null || !isPasswordCorrect(user, loginRequest)) {
            model.addAttribute("message", "Wrong login or password");
            return false;
        } else if (!user.getStatus().equals(Status.ACTIVATED)) {
            model.addAttribute("message", "Account activation is required!");
            return false;
        }
        return true;
    }

    private boolean isPasswordCorrect(User user, LoginRequest loginRequest) {
        String userPassword = user.getHashedPassword();
        String inputPassword = loginRequest.getPassword();

        return userPassword.equals(passwordHashingUtil.hashPassword(inputPassword));
    }

}
