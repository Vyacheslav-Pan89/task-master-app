package com.taskmaster.service;

import com.taskmaster.domain.Status;
import com.taskmaster.domain.CustomUser;
import com.taskmaster.model.LoginRequest;
import com.taskmaster.security.PasswordHashingUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final PasswordHashingUtil passwordHashingUtil;

    public boolean isValidationSucceed(CustomUser user, LoginRequest loginRequest, Model model) {

        if (user == null || !isPasswordCorrect(user, loginRequest)) {
            model.addAttribute("message", "Wrong login or password");
            return false;
        } else if (!user.getStatus().equals(Status.ACTIVATED)) {
            model.addAttribute("message", "Account activation is required!");
            return false;
        }
        return true;
    }

    private boolean isPasswordCorrect(CustomUser user, LoginRequest loginRequest) {
        String userPassword = user.getHashedPassword();
        String inputPassword = loginRequest.getPassword();

        return userPassword.equals(passwordHashingUtil.hashPassword(inputPassword));
    }

}
