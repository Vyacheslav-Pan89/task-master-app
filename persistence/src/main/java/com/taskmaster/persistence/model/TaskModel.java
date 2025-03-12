package com.taskmaster.persistence.model;

import com.taskmaster.persistence.domain.TaskStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskModel {
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
}
