package com.example.hr_system.models.dto;


import java.time.LocalDate;
import lombok.Data;



/**
 * Data Transfer Object (DTO) representing an employee.
 * This class is used to transfer employee data between different layers of the application.
 */

 @Data
 public class EmployeeDto{

    private Long id;
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private String jobTitle;
    private LocalDate hireDate;
    private Double salary;


}
