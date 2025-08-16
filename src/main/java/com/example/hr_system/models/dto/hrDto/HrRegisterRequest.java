package com.example.hr_system.models.dto.hrDto;

import lombok.Data;

@Data
public class HrRegisterRequest {
    private String fullName;
    private String email;
    private String password;
    private String phone;
    private String address;
}