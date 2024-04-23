package com.taskmaster.taskmasterapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comment {

    //TODO: why this class is entity but not table? It should have separate table or it will be nested in other entity?
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String text;

    LocalDate created;

}
