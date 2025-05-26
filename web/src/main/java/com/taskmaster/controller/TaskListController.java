package com.taskmaster.controller;

import com.taskmaster.domain.Task;
import com.taskmaster.domain.CustomUser;
import com.taskmaster.service.TaskService;
import com.taskmaster.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TaskListController {

    private final TaskService taskService;
    private final UserService userService;

    @ModelAttribute("taskList")
    public List<Task> getTaskList() {
        return taskService.allTasks();
    }

    @ModelAttribute("userList")
    public List<CustomUser> getUserList() {
        return userService.getUserList();
    }


    @GetMapping("/task-list")
    public String getTasksList() {
        return "task-list";
    }
}
