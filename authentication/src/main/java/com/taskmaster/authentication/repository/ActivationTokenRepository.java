package com.taskmaster.authentication.repository;

import com.taskmaster.authentication.model.ActivationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivationTokenRepository extends JpaRepository<ActivationToken, Long> {

}
