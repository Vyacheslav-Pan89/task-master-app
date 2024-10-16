package com.taskmaster.taskgenerator.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank(message = "Can't be blank")
    String title;

    @NotBlank(message = "Can't be blank")
    String category;

    @NotBlank(message = "Can't be blank")
    String description;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "task_status")
    TaskStatus taskStatus;

    public Task(String title, String category, String description, TaskStatus taskStatus) {
        this.title = title;
        this.category = category;
        this.description = description;
        this.taskStatus = taskStatus;
    }
}
