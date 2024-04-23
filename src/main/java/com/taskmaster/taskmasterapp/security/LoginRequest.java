package com.taskmaster.taskmasterapp.security;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


//TODO: this class not related to security - please move it to models.
@Setter
@Getter
// TODO: if you are using lombok library please use annotation for constructor also (same for rest models if needed)
public class LoginRequest {


    //Todo: it will more advanced if you will use additional validations - as for now I can enter even one symbol and it will passes
    @NotBlank
    private String userName;
    @NotBlank
    private String password;

    public LoginRequest() {
    }
}
