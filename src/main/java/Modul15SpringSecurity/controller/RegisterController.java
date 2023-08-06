package Modul15SpringSecurity.controller;

import Modul15SpringSecurity.entity.User;
import Modul15SpringSecurity.limitation.UserValidator;
import Modul15SpringSecurity.servise.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class RegisterController {
    private final UserService userService;
    private final UserValidator userValidator;

    @GetMapping("/register")
    public ModelAndView registerPage(){
        return new ModelAndView("register");
    }
    @PostMapping("/register")
    public ModelAndView registerUser(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        List<String> errorsList = userValidator.validateUser(user);
        if (!errorsList.isEmpty()) {
            userService.addNewUser(user);
            modelAndView.setViewName("register");
            modelAndView.addObject("errorsList", errorsList);
        } else {
            userService.addNewUser(user);
            modelAndView.setViewName("redirect:/login");
        }

        return modelAndView;
    }
}

