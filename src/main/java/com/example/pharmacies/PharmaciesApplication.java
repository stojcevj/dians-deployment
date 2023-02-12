package com.example.pharmacies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class PharmaciesApplication {

    public static void main(String[] args) {
        SpringApplication.run(PharmaciesApplication.class, args);
    }

}
