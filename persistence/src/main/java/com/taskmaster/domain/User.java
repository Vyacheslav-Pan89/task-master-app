package com.taskmaster.domain;

import com.taskmaster.model.UserModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_data")
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;

    private String hashedPassword;

    private String email;
    private String fullName;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<UserRole> roles;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private ActivationToken activationToken;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Task> taskList;

    public static User mapToUser(UserModel userDTO) {

        return User.builder()
                .userName(userDTO.getUserName())
                .email(userDTO.getEmail())
                .fullName(userDTO.getFullName())
                .hashedPassword(userDTO.getPassword())
                .status(Status.NOT_ACTIVATED)
                .build();
    }
}
