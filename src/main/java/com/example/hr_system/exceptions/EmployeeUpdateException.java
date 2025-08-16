package com.example.hr_system.exceptions;

public class EmployeeUpdateException extends RuntimeException {
    public EmployeeUpdateException(String message) {
        super(message);
    }

    public EmployeeUpdateException(String message, Throwable cause) {
        super(message, cause);
    }
}
