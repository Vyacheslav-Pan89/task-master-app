package com.taskmaster.taskmasterapp.security;

import com.taskmaster.taskmasterapp.model.LoginRequest;
import com.taskmaster.taskmasterapp.model.User;
import com.taskmaster.taskmasterapp.service.UserService;
import com.taskmaster.taskmasterapp.service.UserServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    private final UserService userService;
    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    //TODO: you are working with MVC so you need to return to client html page.
    // ResponseEntity is ideal when you need to manipulate with data (json, xml, and etc) but it's not your case. Best way is to refactor method's return entity to String and you will able to return 'home' page with validation message from template
    // If you want to set additional info to headers, you can use HttpServletResponse as an input argument as you can see below

    @PostMapping("/loginSubmit")
    public ResponseEntity<String> handleLogin(@Valid @ModelAttribute("loginRequest") LoginRequest loginRequest, BindingResult bindingResult, HttpServletResponse response) {


        //TODO: if user inputs incorrect symbols it should see detailed message and should be returned login page to make another try
        if (bindingResult.hasErrors()) {
            // for instance
            response.setHeader("name of your header", "value of your header");
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
