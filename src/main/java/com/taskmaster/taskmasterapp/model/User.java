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
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Use only letters and numbers. Spaces are ot allowed.")
    String userName;

    @NotBlank(message = "Can't be blank")
    @Size(min = 4, max = 16, message = "Use 4 to 16 symbols")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[!+/=@_])[A-Za-z\\d!+/=@_]+$", message = "Password should consist at least from one numerical symbol" +
            ", one uppercase letter and one special symbol (!+/=@_). Spaces are not allowed!")

    // This field can be removed, as you have hashedPassword field
    @Transient
    String password;

    String hashedPassword;

    @NotBlank(message = "Can't be blank")
    @Email(message = "Enter valid email")
    @Column(unique = true)
    String email;

    @NotBlank(message = "Can't be blank")
    @Pattern(regexp = "[a-zA-Z ]+", message = "Enter your full name only with letters")
    String fullName;

    //TODO: good pattern to use Enum in this case. It will give you more flexibility in future scenarios. Something like: NOT_REGISTERED, PENDING, ACTIVATED
    boolean isActivated;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private ActivationToken activationToken;

}
