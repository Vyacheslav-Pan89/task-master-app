package com.taskmaster.taskgenerator.service;

import com.taskmaster.taskgenerator.model.Task;
import com.taskmaster.taskgenerator.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> allTasks() {
        return taskRepository.findAll();
    }
}
