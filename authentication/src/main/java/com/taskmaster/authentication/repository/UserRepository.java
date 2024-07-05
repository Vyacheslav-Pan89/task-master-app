package com.taskmaster.authentication.repository;

import com.taskmaster.authentication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserName(String userName);

    Optional<User> findByUserNameOrEmail(String username, String email);

    @Query(value = "Select * from user_data as u left join activation_token as t on u.id=t.token_id where t.token=:token", nativeQuery = true)
    Optional<User> findUserByTokenId(@Param("token") String token);

}
