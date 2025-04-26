package com.taskmaster.controller;

import com.taskmaster.model.LoginRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping({"/", "/login"}) //TODO: you can remove '/'
@RequiredArgsConstructor
public class LoginController {

//
//    @ModelAttribute("loginRequest")
//    public LoginRequest getLoginRequest() {
//        return new LoginRequest();
//    }


    //TODO: you can put ModelAttribute as param in get from method. No need to create separate method
    @GetMapping()
    public String login(@ModelAttribute LoginRequest loginRequest) {
        return "login";
    }

    //TODO: твоя валидация в форме не работает, потому что форма получает ошибки с бекенда, но проблема кроется в том, что ты теперь отдал всю ответственность по аутентификации спрингу.
    // В твоём spring security классе у тебя есть loginProcessingUrl(), во-первых он там явно не нужен, но я это отпишу отдельно.
    // Главное что это значит, что твоя форма отправляет запрос на /login, а этот урл указан в loginProcessingUrl() и для спринга это знак, что нужно лезть в БД через UserDetailsService и искать такого юзера.
    // Этим всем занимается фильтр из spring security. Если ты залогинился, то он тебе перекинет на твой /home, а если нет, то он перекинет на /login GET запросом, чтоб показать снова форму логина.
    // handleLogin() вообще никогда не вызовется, а вместе с ним и ошибки не передадутся на html форму через model. У тебя есть два варианта - или делать валидация на фронте через JS или HTML или забрать аутентификацию от спринга вручную на себя.
    // Правильно будет делать валидацию на стороне фронта
    @PostMapping()
    public String handleLogin(@Valid @ModelAttribute("loginRequest") LoginRequest loginRequest,
                              BindingResult bindingResult,
                              Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "login";
        }
        return "redirect:/home";
    }
}
