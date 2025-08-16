package com.example.hr_system.services.servicesImpl;

import com.example.hr_system.exceptions.EmailAlreadyExistsException;
import com.example.hr_system.exceptions.InvalidCredentialsException;
import com.example.hr_system.exceptions.PhoneAlreadyExistsException;
import com.example.hr_system.models.dto.hrDto.HrLoginRequest;
import com.example.hr_system.models.dto.hrDto.HrRegisterRequest;
import com.example.hr_system.models.dto.hrDto.HrResponse;
import com.example.hr_system.models.dto.hrDto.HrTokenResponse;
import com.example.hr_system.models.entity.Hr;
import com.example.hr_system.models.mapper.HrMapper;
import com.example.hr_system.repositories.HrRepository;
import com.example.hr_system.services.HrAuthService;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class HrAuthServiceImpl implements HrAuthService {

    private final HrRepository hrRepository;
    private final PasswordService passwordService; // inject PasswordService
    private final JwtService jwtService; // inject JwtService


    @Override
    public HrResponse register(HrRegisterRequest request) {
        log.info("Attempting to register HR with email: {}", request.getEmail());

        if (hrRepository.findByEmail(request.getEmail()).isPresent()) {
            log.error("Registration failed - email already in use: {}", request.getEmail());
            throw new EmailAlreadyExistsException("Email is already in use: " + request.getEmail());
        }

        if (hrRepository.findByPhone(request.getPhone()).isPresent()) {
            log.error("Registration failed - phone already in use: {}", request.getPhone());
            throw new PhoneAlreadyExistsException("Phone number is already in use: " + request.getPhone());
        }

        // Use PasswordService to encrypt password
        Hr hr = HrMapper.toEntity(request, passwordService.encrypt(request.getPassword()));

        Hr savedHr = hrRepository.save(hr);
        log.info("HR registered successfully - ID: {}, Email: {}", savedHr.getId(), savedHr.getEmail());

        return HrMapper.toResponse(savedHr);
    }

    @Override
    public HrTokenResponse login(HrLoginRequest request) {
        log.info("Attempting login for HR with email: {}", request.getEmail());

        Hr hr = hrRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> {
                    log.error("Login failed - email not found: {}", request.getEmail());
                    return new InvalidCredentialsException("Invalid email or password");
                });

        // Use PasswordService to match passwords
        if (!passwordService.match(request.getPassword(), hr.getPassword())) {
            log.error("Login failed - invalid password for email: {}", request.getEmail());
            throw new InvalidCredentialsException("Invalid email or password");
        }

        log.info("Login successful for HR - ID: {}, Email: {}", hr.getId(), hr.getEmail());
        String token = jwtService.generateToken(hr.getEmail());

        return new HrTokenResponse(HrMapper.toResponse(hr), token);
    }
}