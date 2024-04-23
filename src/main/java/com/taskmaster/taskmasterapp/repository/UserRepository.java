package com.taskmaster.taskmasterapp.repository;

import com.taskmaster.taskmasterapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


//TODO: if you will extend JPARepository instead of CredRepository you can avoid NPE handling by using Optionals - related to all your repos
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);
}
