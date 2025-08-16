package com.example.hr_system.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hr_system.models.entity.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Long>{
     Page<Employee> findByFullNameContainingIgnoreCase(String fullName, Pageable pageable);
     
}
