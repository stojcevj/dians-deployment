package com.example.pharmacies.web;

import com.example.pharmacies.model.exception.InvalidArgumentsException;
import com.example.pharmacies.model.exception.PasswordsDoNotMatchException;
import com.example.pharmacies.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final AuthService authService;


    public RegisterController(AuthService authService) {
        this.authService = authService;
    }
    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String error, Model model) {
        if(error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("bodyContent", "register");
        model.addAttribute("title", "Register");
        return "master";
    }

    @PostMapping
    public String register(@RequestParam String firstName,
                           @RequestParam String lastName,
                           @RequestParam String email,
                           @RequestParam String password,
                           @RequestParam String repeatedPassword) {
        try{
            this.authService.register(firstName,lastName,email,password,repeatedPassword);
            return "redirect:/login";
        } catch (InvalidArgumentsException | PasswordsDoNotMatchException exception) {
            return "redirect:/register?error=" + exception.getMessage();
        }

    }
}