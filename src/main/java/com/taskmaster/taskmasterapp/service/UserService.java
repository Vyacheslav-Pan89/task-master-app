package com.taskmaster.taskmasterapp.service;

import com.taskmaster.taskmasterapp.model.User;
import com.taskmaster.taskmasterapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
