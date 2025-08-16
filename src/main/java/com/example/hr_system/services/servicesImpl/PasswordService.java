package com.example.hr_system.services.servicesImpl;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Method to encrypt password
    public String encrypt(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    // Method to match raw password with encoded password
    public boolean match(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}

