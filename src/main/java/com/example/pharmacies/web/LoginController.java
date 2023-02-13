package com.example.pharmacies.web;


import com.example.pharmacies.model.User;
import com.example.pharmacies.model.exception.InvalidUserCredentialsException;
import com.example.pharmacies.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LoginController {
    public LoginController(AuthService authService) {

    }

    @GetMapping
    public String getLoginPage(Model model) {
        model.addAttribute("bodyContent", "login");
        model.addAttribute("title", "Login");
        return "master";
    }

}