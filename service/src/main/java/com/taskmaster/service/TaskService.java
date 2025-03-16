package com.taskmaster.service;

import com.taskmaster.domain.Task;
import com.taskmaster.repository.TaskRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
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

    public List<Task> getUserAssignedTasks(Long userId) {
        return taskRepository.findByUserId(userId);
    }

    public Task getTaskById(Long id) {
        return taskRepository.getReferenceById(id);
    }

    public void assignTaskByUserId(Long taskId, Long userId) {
        taskRepository.setUserIdToTask(taskId, userId);
    }
}
