package com.example.hr_system.models.mapper;

import com.example.hr_system.models.dto.hrDto.HrRegisterRequest;
import com.example.hr_system.models.dto.hrDto.HrResponse;
import com.example.hr_system.models.entity.Hr;

public class HrMapper {

    // Request → Entity
    public static Hr toEntity(HrRegisterRequest request, String encodedPassword) {
        Hr hr = new Hr();
        hr.setFullName(request.getFullName());
        hr.setEmail(request.getEmail());
        hr.setPassword(encodedPassword);
        hr.setPhone(request.getPhone());
        hr.setAddress(request.getAddress());
        return hr;
    }

    // Entity → Response
    public static HrResponse toResponse(Hr hr) {
        HrResponse response = new HrResponse();
        response.setId(hr.getId());
        response.setFullName(hr.getFullName());
        response.setEmail(hr.getEmail());
        response.setPhone(hr.getPhone());
        response.setAddress(hr.getAddress());
        return response;
    }
}