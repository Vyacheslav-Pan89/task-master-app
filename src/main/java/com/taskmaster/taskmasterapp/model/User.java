package com.taskmaster.taskmasterapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "UserData")
@Table(name = "user_data")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank(message = "Can't be blank")
    @Column(unique = true)
    @Size(min = 2, max = 30)
    String userName;

    @NotBlank(message = "Can't be blank")
    String password;

    @NotBlank(message = "Can't be blank")
    @Email(message = "Enter valid email")
    @Column(unique = true)
    String email;

    @NotBlank(message = "Can't be blank")
    String fullName;

}
