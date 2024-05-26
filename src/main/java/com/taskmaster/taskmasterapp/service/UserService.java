package com.taskmaster.taskmasterapp.service;

import com.taskmaster.taskmasterapp.model.User;
import com.taskmaster.taskmasterapp.repository.UserRepository;
import com.taskmaster.taskmasterapp.security.PasswordHashingUtil;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class UserService {

    private static final String LOGIN_EXIST_MESSAGE = "User with this user name is already registered. Try a different user name";
    private static final String EMAIL_EXIST_MESSAGE = "User with this email is already registered. Try a different email";

    private final UserRepository userRepository;
    private final PasswordHashingUtil passwordHashingUtil;


    @Autowired
    public UserService(UserRepository userRepository, PasswordHashingUtil passwordHashingUtil) {
        this.userRepository = userRepository;
        this.passwordHashingUtil = passwordHashingUtil;
    }


    public User findUserByName(String userName) {
        return userRepository.findByUserName(userName).orElse(null);
    }

    public void add(User user) {

        //TODO: this line should be removed in PROD for security purpose
        System.out.println("Plain password: " + user.getPassword());

        String hashedPassword = passwordHashingUtil.hashPassword(user.getPassword());

        //TODO: no reasons for it to print to console, also can be removed
        System.out.println("Hashed password: " + hashedPassword);

        user.setHashedPassword(hashedPassword);

        try {
            userRepository.save(user);

            //TODO: What is the reason of this catch? Why it should produce this exception?
        } catch (ConstraintViolationException e) {
            System.out.println("Validation failed: " + e.getMessage());
            throw e;
        }
    }

    public User findUserByUserName(String userName) {
        return userRepository.findByUserName(userName).orElse(null);
    }

    public String checkNewUserCredentials(User newUser) {

        return userRepository.findByUserNameOrEmail(newUser.getUserName(), newUser.getEmail())
                .map(user -> user.getUserName().equals(newUser.getUserName()) ? LOGIN_EXIST_MESSAGE : EMAIL_EXIST_MESSAGE)
                .orElse(null);

    }

    //TODO: if you want to print something in console for debugging or testing purpose, please use Logging instead of System.print.ln

}
