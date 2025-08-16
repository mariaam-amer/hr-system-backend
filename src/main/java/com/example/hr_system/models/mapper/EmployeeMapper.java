package com.example.hr_system.models.mapper;

import com.example.hr_system.models.entity.Employee;
import com.example.hr_system.models.dto.EmployeeDto;

public final class EmployeeMapper {

    public static EmployeeDto toDto(Employee employee) {
        if (employee == null) return null;
        EmployeeDto dto = new EmployeeDto();
        dto.setId(employee.getId());
        dto.setFullName(employee.getFullName());
        dto.setEmail(employee.getEmail());
        dto.setPhone(employee.getPhone());
        dto.setAddress(employee.getAddress());
        dto.setJobTitle(employee.getJobTitle());
        dto.setHireDate(employee.getHireDate());
        dto.setSalary(employee.getSalary());
        return dto;
    }

    public static Employee toEntity(EmployeeDto dto) {
        if (dto == null) return null;
        Employee employee = new Employee();
        employee.setId(dto.getId());
        employee.setFullName(dto.getFullName());
        employee.setEmail(dto.getEmail());
        employee.setPhone(dto.getPhone());
        employee.setAddress(dto.getAddress());
        employee.setJobTitle(dto.getJobTitle());
        employee.setHireDate(dto.getHireDate());
        employee.setSalary(dto.getSalary());
        return employee;
    }
}