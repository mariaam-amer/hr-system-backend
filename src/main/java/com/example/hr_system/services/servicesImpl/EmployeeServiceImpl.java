package com.example.hr_system.services.servicesImpl;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.hr_system.exceptions.EmployeeCreationException;
import com.example.hr_system.exceptions.EmployeeNotFoundException;
import com.example.hr_system.exceptions.EmployeeUpdateException;
import com.example.hr_system.models.dto.EmployeeDto;
import com.example.hr_system.models.entity.Employee;
import com.example.hr_system.models.mapper.EmployeeMapper;
import com.example.hr_system.repositories.EmployeeRepository;
import com.example.hr_system.services.EmployeeService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;


    @Override
    public EmployeeDto addEmployee(EmployeeDto dto) {

        log.info("Adding employee: {}", dto);
    try {
        Employee employee = EmployeeMapper.toEntity(dto);

        //Save the employee to get the ID 
        EmployeeDto newEmployee = EmployeeMapper.toDto(employeeRepository.save(employee));
        log.info("Employee added successfully: {}", newEmployee.getId());
        
        return newEmployee;
    } catch (Exception e) {
        log.error("Error adding employee: {}", e.getMessage(), e);

        throw new EmployeeCreationException("Failed to add employee", e);

    }
}



    @Override
    public EmployeeDto updateEmployee(Long id, EmployeeDto dto) {
        log.info("Updating employee with ID: {}", id);

        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with ID: " + id));

        try {
        // update existing employee instead of create new one
            existingEmployee.setFullName(dto.getFullName());
            existingEmployee.setEmail(dto.getEmail());
            existingEmployee.setPhone(dto.getPhone());
            existingEmployee.setAddress(dto.getAddress());
            existingEmployee.setJobTitle(dto.getJobTitle());
            existingEmployee.setHireDate(dto.getHireDate());
            existingEmployee.setSalary(dto.getSalary());

            EmployeeDto updatedDto = EmployeeMapper.toDto(employeeRepository.save(existingEmployee));
            log.info("Employee updated successfully - ID: {}, Name: {}", 
                      updatedDto.getId(), updatedDto.getFullName());
            return updatedDto;
        } catch (Exception e) {
            log.error("Error updating employee with ID {}: {}", id, e.getMessage(), e);
            throw new EmployeeUpdateException("Failed to update employee", e);
        }   
    }


    @Override
    public void deleteEmployee(Long id) {
        log.info("Deleting employee with ID: {}", id);

        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with ID: " + id));

        employeeRepository.delete(existingEmployee);
        log.info("Successfully deleted employee with ID: {}", id);
    }


    @Override
    public Page<EmployeeDto> getAllEmployees(Pageable pageable) {
        log.info("Fetching all employees, page: {}, size: {}", pageable.getPageNumber(), pageable.getPageSize());

        Page<Employee> employeesPage = employeeRepository.findAll(pageable);

        Page<EmployeeDto> dtoPage = employeesPage.map(EmployeeMapper::toDto);

        log.debug("Fetched {} employees", employeesPage.getNumberOfElements());
        return dtoPage;
    }
    @Override
    public EmployeeDto getEmployeeById(Long id) {
        log.info("Fetching employee with ID: {}", id);

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with ID: " + id));

        return EmployeeMapper.toDto(employee);
    }

    @Override
    public Page<EmployeeDto> searchEmployeesByName(String name, Pageable pageable) {
    log.info("Searching employees by name: {}", name);

    Page<Employee> employeesPage = employeeRepository.findByFullNameContainingIgnoreCase(name, pageable);

    return employeesPage.map(EmployeeMapper::toDto);
}








    
}
