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

    public boolean userNameExist(User user) {
        List<User> userList = userRepository.findAll();
        for (User obj : userList) {
            if (obj.getUserName().equals(user.getUserName())) {
                return true;
            }
        }
        return false;
    }

    public boolean emailExist(User user) {
        List<User> userList = userRepository.findAll();
        for(User obj : userList){
            if (obj.getEmail().equals(user.getEmail())) {
                return true;
            }
        }
        return false;
    }
}
