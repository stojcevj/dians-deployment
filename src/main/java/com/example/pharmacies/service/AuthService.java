package com.example.pharmacies.service;

import com.example.pharmacies.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {
    User login(String email, String password);
    User register(String firstName,String lastName,String email,String password,String repeatedPassword);
}
