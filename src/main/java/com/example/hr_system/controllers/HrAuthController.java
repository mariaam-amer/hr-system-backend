package com.example.hr_system.controllers;

import com.example.hr_system.models.dto.hrDto.HrLoginRequest;
import com.example.hr_system.models.dto.hrDto.HrRegisterRequest;
import com.example.hr_system.models.dto.hrDto.HrResponse;
import com.example.hr_system.models.dto.hrDto.HrTokenResponse;
import com.example.hr_system.services.HrAuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "HR Authentication", description = "APIs for HR user registration and login")
public class HrAuthController {

    private final HrAuthService hrAuthService;

    
     // Register a new HR user
    @Operation(summary = "Register HR", description = "Register a new HR user")
    @PostMapping("/register")
    public ResponseEntity<HrResponse> register(@RequestBody HrRegisterRequest request) {
        return ResponseEntity.ok(hrAuthService.register(request));
    }


    // Login an HR user and return a Jwt token
    @Operation(summary = "Login HR", description = "Authenticate HR and return JWT token")
    @PostMapping("/login")
    public ResponseEntity<HrTokenResponse> login(@RequestBody HrLoginRequest request) {
        HrTokenResponse response = hrAuthService.login(request);
        return ResponseEntity.ok(response);
    }

} 
