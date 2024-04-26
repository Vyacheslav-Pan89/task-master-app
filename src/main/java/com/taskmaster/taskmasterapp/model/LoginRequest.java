package com.taskmaster.taskmasterapp.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @NotBlank(message = "Can't be blank")
    @Size(min = 4, max = 16, message = "Use 4 to 16 symbols")
    private String userName;

    @Size(min = 4, max = 16, message = "Use 4 to 16 symbols")
    @NotBlank(message = "Can't be blank")
    private String password;

}
