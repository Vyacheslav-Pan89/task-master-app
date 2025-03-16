package com.taskmaster.repository;

import com.taskmaster.domain.Task;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByUserId(Long userId);

    @Transactional
    @Modifying
    @Query("UPDATE Task t SET t.user.id = :userId WHERE t.id = :taskId")
    void setUserIdToTask(@Param("taskId")Long taskId,@Param("userId") Long userId);
}
