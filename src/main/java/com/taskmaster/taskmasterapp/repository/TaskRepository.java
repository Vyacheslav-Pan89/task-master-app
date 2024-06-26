package com.taskmaster.taskmasterapp.repository;

import com.taskmaster.taskmasterapp.model.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
}
