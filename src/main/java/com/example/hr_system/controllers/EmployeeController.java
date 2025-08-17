package com.example.hr_system.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.hr_system.models.dto.EmployeeDto;
import com.example.hr_system.services.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Employee Management", description = "APIs for managing employees")
public class EmployeeController {
    // This class will handle HTTP requests related to Employee operations
    // It will use EmployeeService to perform CRUD operations on Employee entities

    // And methods for:
    // - getEmployeeById(Long id)
    // - searchEmployeesByName(String name)

    private final EmployeeService employeeService;

    // Create a new employee
    @Operation(summary = "Create new employee", description = "Add a new employee to the system")
    @PostMapping
    public ResponseEntity<EmployeeDto> addEmployee(@RequestBody EmployeeDto dto) {
        log.info("Received request to add employee: {}", dto);
        EmployeeDto createdEmployee = employeeService.addEmployee(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployee);
    }

    // Update an existing employee
    @Operation(summary = "Update employee", description = "Update existing employee by ID")
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(
        @PathVariable Long id,
        @RequestBody EmployeeDto dto) {

    log.info("Received request to update employee with ID: {}, Data: {}", id, dto);
    EmployeeDto updatedEmployee = employeeService.updateEmployee(id, dto);
    return ResponseEntity.ok(updatedEmployee); // 200 OK
    }

    // Delete an employee by ID
    @Operation(summary = "Delete employee", description = "Delete employee by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {

        log.info("Received request to delete employee with ID: {}", id);
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("Employee deleted successfully");
    }

    // Get all employees with pagination
    //http://localhost:8080/api/employees?page=0&size=5&sort=id,desc
    @Operation(summary = "List employees", description = "Get all employees with pagination")
    @GetMapping
    public ResponseEntity<Page<EmployeeDto>> getAllEmployees(Pageable pageable) {

        log.info("Received request to get all employees with pagination: {}", pageable);
        Page<EmployeeDto> employees = employeeService.getAllEmployees(pageable);
        return ResponseEntity.ok(employees); // 200 OK
    }

    // Get employee by ID
    @Operation(summary = "Get employee", description = "Get employee by ID")
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long id) {

        log.info("Received request to get employee by ID: {}", id);
        EmployeeDto employee = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employee); // 200 OK
    }

    // Search employees by name with pagination
    //http://localhost:8080/api/employees/search?name=a&page=0&size=5&sort=id,desc
    @Operation(summary = "Search employees", description = "Search employees by full name")
    @GetMapping("/search")
    public ResponseEntity<Page<EmployeeDto>> searchEmployeesByName(
            @RequestParam String name,
            Pageable pageable) {
 
        log.info("Received request to search employees by name: {}, Pagination: {}", name, pageable);
        Page<EmployeeDto> employees = employeeService.searchEmployeesByName(name, pageable);
        return ResponseEntity.ok(employees); // 200 OK
    }



}
