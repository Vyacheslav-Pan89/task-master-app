package com.taskmaster.service.service;

import com.taskmaster.persistence.domain.Task;
import com.taskmaster.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public List<Task> allTasks() {
        return taskRepository.findAll();
    }

    public void add(Task task) {
        taskRepository.save(task);
    }
}
