package com.taskmaster.service;

import com.taskmaster.domain.ActivationToken;
import com.taskmaster.domain.CustomUser;
import com.taskmaster.domain.Status;
import com.taskmaster.model.UserModel;
import com.taskmaster.repository.ActivationTokenRepository;
import com.taskmaster.repository.UserRepository;
import com.taskmaster.security.PasswordHashingUtil;
import com.taskmaster.security.TokenGenerator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.PrimitiveIterator;

@Service
@RequiredArgsConstructor
public class UserService {

    private static final String LOGIN_EXIST_MESSAGE = "User with this user name is already registered. Try a different user name";
    private static final String EMAIL_EXIST_MESSAGE = "User with this email is already registered. Try a different email";

    private final TokenGenerator tokenGenerator;
    private final UserRepository userRepository;
    private final PasswordHashingUtil passwordHashingUtil;
    private final ActivationTokenRepository activationTokenRepository;
    private final EmailService emailService;

    public CustomUser findUserByName(String userName) {
        return userRepository.findByUserName(userName).orElse(null);
    }

    public void add(CustomUser user) {

        String hashedPassword = passwordHashingUtil.hashPassword(user.getHashedPassword());
        user.setHashedPassword(hashedPassword);

        ActivationToken activationToken = new ActivationToken();
        activationToken.setToken(tokenGenerator.generateToken());
        activationToken.setUser(user);
        user.setActivationToken(activationToken);

        userRepository.save(user);
        activationTokenRepository.save(activationToken);
    }

    public CustomUser findUserByUserName(String userName) {
        return userRepository.findByUserName(userName).orElse(null);
    }

    public String checkNewUserCredentials(CustomUser newUser) {

        return userRepository.findByUserNameOrEmail(newUser.getUserName(), newUser.getEmail())
                .map(user -> user.getUserName().equals(newUser.getUserName()) ? LOGIN_EXIST_MESSAGE : EMAIL_EXIST_MESSAGE)
                .orElse(null);

    }

    @Transactional
    public void activateUser(String token) {

        CustomUser user = userRepository.findUserByTokenId(token).orElse(null);
        if (user != null) {
            user.setStatus(Status.ACTIVATED);
            userRepository.save(user);
        }

    }

    public List<CustomUser> getUserList() {
        return userRepository.findAll();
    }

    public String processUser(UserModel userDTO) {
        CustomUser user = CustomUser.mapToUser(userDTO);
        String validationMessage = checkNewUserCredentials(user);
        if(validationMessage != null){
            return validationMessage;
        }
        add(user);
        emailService.sendEmail(user);
        return null;
    }
}
