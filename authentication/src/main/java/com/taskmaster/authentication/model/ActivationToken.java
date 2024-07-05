package com.taskmaster.authentication.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "activation_token")
public class ActivationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id")
    Long id;

    String token;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    User user;
}
