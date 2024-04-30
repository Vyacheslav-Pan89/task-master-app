package com.taskmaster.taskmasterapp.repository;

import com.taskmaster.taskmasterapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

     //TODO: FYI, I have changed returned to value to Optional wrapper. It's more flexible for avoiding NPE
     Optional <User> findByUserName(String userName);
     Optional<User> findByUserNameOrEmail(String username, String email);

}
