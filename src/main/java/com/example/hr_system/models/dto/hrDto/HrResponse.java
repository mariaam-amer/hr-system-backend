package com.example.hr_system.models.dto.hrDto;

import lombok.Data;

@Data
public class HrResponse {
    private Long id;
    private String fullName;
    private String email;
    private String phone;
    private String address;
}