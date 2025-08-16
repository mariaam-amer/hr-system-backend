package com.example.hr_system.services;

import com.example.hr_system.models.dto.hrDto.HrLoginRequest;
import com.example.hr_system.models.dto.hrDto.HrRegisterRequest;
import com.example.hr_system.models.dto.hrDto.HrResponse;
import com.example.hr_system.models.dto.hrDto.HrTokenResponse;

public interface HrAuthService {
    HrResponse register(HrRegisterRequest request);
    HrTokenResponse login(HrLoginRequest request);
}
