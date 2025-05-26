package com.taskmaster.repository;

import com.taskmaster.domain.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<CustomUser, Long> {

    Optional<CustomUser> findByUserName(String userName);

    Optional<CustomUser> findByUserNameOrEmail(String username, String email);

    @Query(value = "Select * from user_data as u left join activation_token as t on u.id=t.token_id where t.token=:token", nativeQuery = true)
    Optional<CustomUser> findUserByTokenId(@Param("token") String token);

}
