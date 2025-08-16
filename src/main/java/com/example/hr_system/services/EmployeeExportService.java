package com.example.hr_system.services;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public interface EmployeeExportService {
    ByteArrayInputStream exportEmployees() throws IOException;
}
