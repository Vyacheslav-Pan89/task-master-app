package com.taskmaster.taskmasterapp.service;


import com.taskmaster.taskmasterapp.model.LoginRequest;
import com.taskmaster.taskmasterapp.model.Status;
import com.taskmaster.taskmasterapp.model.User;
import com.taskmaster.taskmasterapp.security.PasswordHashingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final PasswordHashingUtil passwordHashingUtil;

    @Autowired
    public LoginService(PasswordHashingUtil passwordHashingUtil) {
        this.passwordHashingUtil = passwordHashingUtil;
    }

    public String loginPermission(User user, LoginRequest loginRequest) {

        if (user == null) {
            return "Wrong login or password";
        }
        if (!user.getHashedPassword().equals(passwordHashingUtil.hashPassword(loginRequest.getPassword()))) {
            return "Wrong login or password";
        }
        if (!user.getStatus().equals(Status.ACTIVATED)) {
            return "Account activation is required!";
        }

        return "OK";

    }

}
