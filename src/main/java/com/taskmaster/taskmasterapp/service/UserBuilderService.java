package com.taskmaster.taskmasterapp.service;

import com.taskmaster.taskmasterapp.model.Status;
import com.taskmaster.taskmasterapp.model.User;
import com.taskmaster.taskmasterapp.model.UserDTO;
import org.springframework.stereotype.Service;

@Service
public class UserBuilderService {

    public User buildUser(UserDTO userDTO) {

        User user = User.builder()
                .userName(userDTO.getUserName())
                .email(userDTO.getEmail())
                .fullName(userDTO.getFullName())
                .hashedPassword(userDTO.getPassword())
                .status(Status.NOT_ACTIVATED)
                .build();

        return user;
    }
}
