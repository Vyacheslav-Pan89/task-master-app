package com.taskmaster.service.service;

import com.taskmaster.persistence.domain.Status;
import com.taskmaster.persistence.domain.User;
import com.taskmaster.persistence.model.LoginRequest;
import com.taskmaster.service.security.PasswordHashingUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final PasswordHashingUtil passwordHashingUtil;

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
