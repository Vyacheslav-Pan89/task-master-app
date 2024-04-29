package com.taskmaster.taskmasterapp.service;

import com.taskmaster.taskmasterapp.model.User;
import com.taskmaster.taskmasterapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User findUserByName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public void add(User user) {
        userRepository.save(user);
    }

    public Object findUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }


    // TODO: You can combine these 2 fields check and please use streams DONE!!!

    public String checkNewUserCredentials(User newUser) {

        List<User> userList = userRepository.findAll();

        boolean userNameExist = userList.stream().anyMatch(user -> user.getUserName().equals(newUser.getUserName()));
        boolean emailExist = userList.stream().anyMatch(user -> user.getEmail().equals(newUser.getEmail()));

        if(userNameExist){
            return "User with this user name is already registered. Try a different user name";
        } else if (emailExist) {
            return "User with this email is already registered. Try a different email";
        }
        return null;
    }
}
