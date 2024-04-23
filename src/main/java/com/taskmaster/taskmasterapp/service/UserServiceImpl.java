package com.taskmaster.taskmasterapp.service;

import com.taskmaster.taskmasterapp.model.User;
import com.taskmaster.taskmasterapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


//TODO: in context of your logic you don't need to make UserService interface and it's implementation. Please remove interface, rename UserServiceImpl to userService - related to all your services
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User findUserByName(String userName) {
        return userRepository.findByUserName(userName);
    }
}
