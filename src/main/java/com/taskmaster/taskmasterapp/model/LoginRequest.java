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


    //Todo: it will more advanced if you will use additional validations - as for now I can enter even one symbol and it will passes DONE!!!

    @NotBlank
    @Size(min = 4, max = 16, message = "Use 4 to 16 symbols")
    private String userName;

    //@Size(min = 8, max = 16, message = "Use 8 to 16 symbols") // commented for test purpose. uncomment after test
    @NotBlank
    private String password;

}
