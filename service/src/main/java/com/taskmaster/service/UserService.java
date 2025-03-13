package com.taskmaster.service;

import com.taskmaster.domain.ActivationToken;
import com.taskmaster.domain.Status;
import com.taskmaster.domain.User;
import com.taskmaster.exception.UsernameNotFoundException;
import com.taskmaster.repository.ActivationTokenRepository;
import com.taskmaster.repository.UserRepository;
import com.taskmaster.security.PasswordHashingUtil;
import com.taskmaster.security.TokenGenerator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private static final String LOGIN_EXIST_MESSAGE = "User with this user name is already registered. Try a different user name";
    private static final String EMAIL_EXIST_MESSAGE = "User with this email is already registered. Try a different email";

    private final TokenGenerator tokenGenerator;
    private final UserRepository userRepository;
    private final PasswordHashingUtil passwordHashingUtil;
    private final ActivationTokenRepository activationTokenRepository;

    public User findUserByName(String userName) {
        return userRepository.findByUserName(userName).orElseThrow(() ->
                new UsernameNotFoundException("User not found"));
    }

    public void add(User user) {

        String hashedPassword = passwordHashingUtil.hashPassword(user.getHashedPassword());
        user.setHashedPassword(hashedPassword);

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

        User user = userRepository.findUserByTokenId(token).orElse(null);
        if (user != null) {
            user.setStatus(Status.ACTIVATED);
            userRepository.save(user);
        }

    }

}
