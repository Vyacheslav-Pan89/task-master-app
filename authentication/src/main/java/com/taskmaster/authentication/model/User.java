package com.taskmaster.authentication.model;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
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

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private ActivationToken activationToken;

    public static User mapToUser(UserDTO userDTO) {

        return User.builder()
                .userName(userDTO.getUserName())
                .email(userDTO.getEmail())
                .fullName(userDTO.getFullName())
                .hashedPassword(userDTO.getPassword())
                .status(Status.NOT_ACTIVATED)
                .build();
    }
}
