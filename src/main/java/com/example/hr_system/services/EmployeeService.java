package com.example.hr_system.services;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.hr_system.models.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto addEmployee(EmployeeDto dto);
    EmployeeDto updateEmployee(Long id, EmployeeDto dto);
    void deleteEmployee(Long id);
    Page<EmployeeDto> getAllEmployees(Pageable pageable);
    EmployeeDto getEmployeeById(Long id);
    Page<EmployeeDto> searchEmployeesByName(String name, Pageable pageable);

}
