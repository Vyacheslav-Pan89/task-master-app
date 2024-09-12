package com.taskmaster.taskgenerator.repository;

import com.taskmaster.taskgenerator.model.Task;
import com.taskmaster.taskgenerator.model.TaskStatus;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//TODO: no, please use SQL scripts for initial table initialization with data. Read about data.sql and schema.sql
@Component
public class TaskLoader implements CommandLineRunner {

    private final TaskRepository taskRepository;

    public TaskLoader(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void run(String... args) {
        taskRepository.save(new Task("Finish Spring Boot Tutorial",
                "Work", "Complete the remaining sections of the Spring Boot guide.",
                TaskStatus.NOT_STARTED));
        taskRepository.save(new Task("Buy Groceries",
                "Shopping", "Pick up essentials like milk, bread, and eggs.",
                TaskStatus.NOT_STARTED));
        taskRepository.save(new Task("Read Java Concurrency in Practice",
                "Personal Development", "Read the first two chapters of the book.",
                TaskStatus.NOT_STARTED));
        taskRepository.save(new Task("Clean Workspace",
                "Household", "Organize desk and clean clutter from workspace.",
                TaskStatus.NOT_STARTED));
        taskRepository.save(new Task("Team Meeting Preparation",
                "Work", "Prepare agenda for the upcoming team meeting.",
                TaskStatus.NOT_STARTED));
        taskRepository.save(new Task("Yoga Practice",
                "Fitness", "Complete a 30-minute morning yoga routine.",
                TaskStatus.NOT_STARTED));
        taskRepository.save(new Task("Plan Weekend Trip",
                "Personal", "Research locations and activities for a weekend getaway.",
                TaskStatus.NOT_STARTED));
        taskRepository.save(new Task("Update LinkedIn Profile",
                "Work", "Add recent projects and update professional summary.",
                TaskStatus.NOT_STARTED));
        taskRepository.save(new Task("Review Java Collections Framework",
                "Study", "Revise the main Java collections, focusing on maps and sets.",
                TaskStatus.NOT_STARTED));
        taskRepository.save(new Task("Declutter Email Inbox",
                "Personal", "Organize and delete unnecessary emails from inbox.",
                TaskStatus.NOT_STARTED));
    }
}
