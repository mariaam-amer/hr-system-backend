package com.example.hr_system.models.dto.hrDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HrTokenResponse {
    private HrResponse hr;
    private String token;
}

