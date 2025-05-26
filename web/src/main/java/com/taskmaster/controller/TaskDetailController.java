package com.taskmaster.controller;

import com.taskmaster.domain.Task;
import com.taskmaster.domain.CustomUser;
import com.taskmaster.service.TaskService;
import com.taskmaster.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TaskDetailController {

    private final TaskService taskService;
    private final UserService userService;

    @ModelAttribute("userList")
    public List<CustomUser> getUserList() {
        return userService.getUserList();
    }


    @GetMapping("/task-details/{id}")
    public String getTasksDetails(@PathVariable("id") Long id, Model model) {
        Task task = taskService.getTaskById(id);
        if (task == null) {
            return "error";
        }
        model.addAttribute("task", task);
        return "task-details";
    }

    @PostMapping("/task-details/assign")
    public String assignTask(@RequestParam("taskId") Long taskId, @RequestParam("userId") Long userId) {
        taskService.assignTaskByUserId(taskId, userId);
        return "redirect:/task-list";
    }
}
