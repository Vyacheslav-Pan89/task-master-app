package com.taskmaster.controller;

import com.taskmaster.domain.Task;
import com.taskmaster.domain.User;
import com.taskmaster.repository.TaskRepository;
import com.taskmaster.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class TaskController {


//    @PostMapping("/tasks")
//    public String addTask(@Valid Task task, BindingResult bindingResult, Model model) {
//        if (bindingResult.hasErrors()) {
//            model.addAttribute("errors", bindingResult.getAllErrors());
//            return "some string";
//        }
//        taskService.add(task);
//        return "some string";
//    }

}
