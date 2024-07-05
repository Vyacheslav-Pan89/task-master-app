package com.taskmaster.taskmasterapp.model;

import jakarta.persistence.Column;
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
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @NotBlank(message = "Can't be blank")
    @Column(unique = true)
    @Size(min = 4, max = 16, message = "Use 4 to 16 symbols")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Use only letters and numbers. Spaces are ot allowed.")
    private String userName;

    @NotBlank(message = "Can't be blank")
    @Size(min = 4, max = 16, message = "Use 4 to 16 symbols")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[!+/=@_])[A-Za-z\\d!+/=@_]+$", message = "Password should consist at least from one numerical symbol" +
            ", one uppercase letter and one special symbol (!+/=@_). Spaces are not allowed!")
    private String password;

    @NotBlank(message = "Can't be blank")
    @Email(message = "Enter valid email")
    private String email;

    @NotBlank(message = "Can't be blank")
    @Pattern(regexp = "[a-zA-Z ]+", message = "Enter your full name only with letters")
    private String fullName;

}
