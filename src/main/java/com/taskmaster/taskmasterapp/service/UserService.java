package com.taskmaster.taskmasterapp.service;

import com.taskmaster.taskmasterapp.model.ActivationToken;
import com.taskmaster.taskmasterapp.model.User;
import com.taskmaster.taskmasterapp.repository.ActivationTokenRepository;
import com.taskmaster.taskmasterapp.repository.UserRepository;
import com.taskmaster.taskmasterapp.security.PasswordHashingUtil;
import com.taskmaster.taskmasterapp.security.TokenGenerator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class UserService {

    private static final String LOGIN_EXIST_MESSAGE = "User with this user name is already registered. Try a different user name";
    private static final String EMAIL_EXIST_MESSAGE = "User with this email is already registered. Try a different email";

    private final TokenGenerator tokenGenerator;
    private final UserRepository userRepository;
    private final PasswordHashingUtil passwordHashingUtil;
    private final ActivationTokenRepository activationTokenRepository;


    @Autowired
    public UserService(TokenGenerator tokenGenerator, UserRepository userRepository, PasswordHashingUtil passwordHashingUtil, ActivationTokenRepository activationTokenRepository) {
        this.tokenGenerator = tokenGenerator;
        this.userRepository = userRepository;
        this.passwordHashingUtil = passwordHashingUtil;
        this.activationTokenRepository = activationTokenRepository;
    }


    public User findUserByName(String userName) {
        return userRepository.findByUserName(userName).orElse(null);
    }

    public void add(User user) {

        String hashedPassword = passwordHashingUtil.hashPassword(user.getPassword());
        user.setHashedPassword(hashedPassword);

        user.setActivated(false);

        ActivationToken activationToken = new ActivationToken();
        activationToken.setToken(tokenGenerator.generateToken());
        activationToken.setUser(user);
        user.setActivationToken(activationToken);

        userRepository.save(user);
        activationTokenRepository.save(activationToken);
    }

    public User findUserByUserName(String userName) {
        return userRepository.findByUserName(userName).orElse(null);
    }

    public String checkNewUserCredentials(User newUser) {

        return userRepository.findByUserNameOrEmail(newUser.getUserName(), newUser.getEmail())
                .map(user -> user.getUserName().equals(newUser.getUserName()) ? LOGIN_EXIST_MESSAGE : EMAIL_EXIST_MESSAGE)
                .orElse(null);

    }

    @Transactional
    public void activateUser(String token) {

        // TODO: Need to think over user activation logic. Till now had 500 errors

    }

    //TODO: if you want to print something in console for debugging or testing purpose, please use Logging instead of System.print.ln

}
