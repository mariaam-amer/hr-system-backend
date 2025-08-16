package com.example.hr_system.models.dto.hrDto;

import lombok.Data;

@Data
public class HrLoginRequest {
    private String email;
    private String password;
}