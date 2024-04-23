package com.taskmaster.taskmasterapp.security;

import com.taskmaster.taskmasterapp.model.User;
import com.taskmaster.taskmasterapp.service.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    private final UserServiceImpl userService;
    @Autowired
    public LoginController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/loginSubmit")
    public ResponseEntity<String> handleLogin(@Valid @ModelAttribute("loginRequest") LoginRequest loginRequest, BindingResult bindingResult) {

        //TODO: if user inputs incorrect symbols it should see detailed message and should be returned login page to make another try
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Access Denied");
        }

        User user = userService.findUserByName(loginRequest.getUserName());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .header("Location", "/home")
                    .body("Invalid Username or Password");
        }

        if (!user.getPassword().equals(loginRequest.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .header("Location", "/home")
                    .body("Invalid Username or Password");
        }

        return ResponseEntity.ok("Login Successful");
    }
}
