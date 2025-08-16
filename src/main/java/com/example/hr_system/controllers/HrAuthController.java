package com.example.hr_system.controllers;

import com.example.hr_system.models.dto.hrDto.HrLoginRequest;
import com.example.hr_system.models.dto.hrDto.HrRegisterRequest;
import com.example.hr_system.models.dto.hrDto.HrResponse;
import com.example.hr_system.models.dto.hrDto.HrTokenResponse;
import com.example.hr_system.services.HrAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

/**
 * Handles registration and login of HR users.
 */
@RestController
@RequestMapping("/api/hr/auth")
@RequiredArgsConstructor
@Slf4j
public class HrAuthController {

    private final HrAuthService hrAuthService;

    
     // Register a new HR user
    @PostMapping("/register")
    public ResponseEntity<HrResponse> register(@RequestBody HrRegisterRequest request) {
        return ResponseEntity.ok(hrAuthService.register(request));
    }


    // Login an HR user and return a Jwt token
    @PostMapping("/login")
    public ResponseEntity<HrTokenResponse> login(@RequestBody HrLoginRequest request) {
        HrTokenResponse response = hrAuthService.login(request);
        return ResponseEntity.ok(response);
    }

} 
