package com.example.pharmacies.service;

import com.example.pharmacies.model.User;

public interface AuthService {
    User login(String email, String password);
    User register(String firstName,String lastName,String email,String password,String repeatedPassword);
}
