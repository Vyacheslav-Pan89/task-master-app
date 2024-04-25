package com.taskmaster.taskmasterapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_data")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank(message = "Can't be blank")
    @Column(unique = true)
    @Size(min = 4, max = 16, message = "Use 4 to 16 symbols")
    String userName;

    @NotBlank(message = "Can't be blank")
    //@Size(min = 8, max = 16, message = "Use 8 to 16 symbols") // commented for test purpose. uncomment after test
    String password;

    @NotBlank(message = "Can't be blank")
    @Email(message = "Enter valid email")
    @Column(unique = true)
    String email;

    @NotBlank(message = "Can't be blank")
    @Pattern(regexp = "[a-zA-Z ]+", message = "Enter your full name only with letters")
    String fullName;

}
