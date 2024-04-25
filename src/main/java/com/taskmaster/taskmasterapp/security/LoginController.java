package com.taskmaster.taskmasterapp.security;

import com.taskmaster.taskmasterapp.model.LoginRequest;
import com.taskmaster.taskmasterapp.model.User;
import com.taskmaster.taskmasterapp.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

//TODO; this class not related to security. Moved it to controller package
@Controller
public class LoginController {

    private final UserService userService;
    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String login(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return "login";
    }

    //TODO: you are working with MVC so you need to return to client html page.
    // ResponseEntity is ideal when you need to manipulate with data (json, xml, and etc) but it's not your case.
    // Best way is to refactor method's return entity to String and you will able to return 'home' page with validation message from template
    // If you want to set additional info to headers, you can use HttpServletResponse as an input argument as you can see below

    @PostMapping("/loginSubmit")
    public String handleLogin(@Valid @ModelAttribute("loginRequest") LoginRequest loginRequest,
                              BindingResult bindingResult,
                              Model model) {

        //TODO: if user inputs incorrect symbols it should see detailed message and should be returned login page to make another try


        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "login";
        }

        User user = userService.findUserByName(loginRequest.getUserName());
        if (user == null) {
            model.addAttribute("message", "Wrong login or password");
            return "login";
        }

        if (!user.getPassword().equals(loginRequest.getPassword())) {
            model.addAttribute("message", "Wrong login or password");
            return "login";
        }

        model.addAttribute("user", userService.findUserByUserName(loginRequest.getUserName()));
        return "redirect:/home";
    }
}
