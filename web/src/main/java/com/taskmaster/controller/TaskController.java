package com.taskmaster.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
