package com.taskmaster.taskmasterapp.service;

import com.taskmaster.taskmasterapp.model.User;
import com.taskmaster.taskmasterapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final String LOGIN_EXIST_MESSAGE = "User with this user name is already registered. Try a different user name";
    private static final String EMAIL_EXIST_MESSAGE = "User with this email is already registered. Try a different email";

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User findUserByName(String userName) {
        return userRepository.findByUserName(userName).orElse(null);
    }

    public void add(User user) {
        userRepository.save(user);
    }


    //TODO: why object but not User?
    public Object findUserByUserName(String userName) {
        return userRepository.findByUserName(userName).orElse(null);
    }


    public String checkNewUserCredentials(User newUser) {


        //TODO: more concise implementation for checking email of username. Repository will return optional
        // If it's present it will map with functional pattern correct return message and if not present will return null.
        // It's not a remark, just more advanced implementation to share knowledge with you)))
        return userRepository.findByUserNameOrEmail(newUser.getUserName(), newUser.getEmail())
                .map(user -> user.getUserName().equals(newUser.getUserName()) ? LOGIN_EXIST_MESSAGE : EMAIL_EXIST_MESSAGE)
                .orElse(null);


//        List<User> userList = userRepository.findAll();
//
//        boolean userNameExist = userList.stream().anyMatch(user -> user.getUserName().equals(newUser.getUserName()));
//        boolean emailExist = userList.stream().anyMatch(user -> user.getEmail().equals(newUser.getEmail()));
//
//        if(userNameExist){
//            return "User with this user name is already registered. Try a different user name";
//        } else if (emailExist) {
//            return "User with this email is already registered. Try a different email";
//        }
//        return null;
    }

}
