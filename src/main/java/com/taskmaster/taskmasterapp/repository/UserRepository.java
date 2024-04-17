package com.taskmaster.taskmasterapp.repository;

import com.taskmaster.taskmasterapp.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
