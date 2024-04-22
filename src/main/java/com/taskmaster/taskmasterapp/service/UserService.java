package com.taskmaster.taskmasterapp.service;

import com.taskmaster.taskmasterapp.model.User;

public interface UserService {

    User findUserByName(String userName);

}
