package com.taskmaster.taskmasterapp.repository;

import com.taskmaster.taskmasterapp.model.ActivationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivationTokenRepository extends JpaRepository<ActivationToken, Long> {

}
