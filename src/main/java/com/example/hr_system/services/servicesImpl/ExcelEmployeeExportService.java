package com.example.hr_system.services.servicesImpl;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.example.hr_system.models.entity.Employee;
import com.example.hr_system.repositories.EmployeeRepository;
import com.example.hr_system.services.EmployeeExportService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

// Named bean for clarity in case of multiple export services
@Service("excelExportService") 
public class ExcelEmployeeExportService implements EmployeeExportService {

    private final EmployeeRepository employeeRepository;

    public ExcelEmployeeExportService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public ByteArrayInputStream exportEmployees() throws IOException {
        List<Employee> employees = employeeRepository.findAll();

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Employees");

          
        String[] headers = {
                "ID", "", 
                "Full Name", "", 
                "Email", "", "",
                "Phone", "", "",
                "Address", "", "",
                "Job Title", "", "",
                "Hire Date", "", "",
                "Salary"
    };

        Row headerRow = sheet.createRow(0);

        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        sheet.addMergedRegion(new CellRangeAddress(0, 0, 4, 6));

        int rowIdx = 1;

        for (Employee emp : employees) {
            Row row = sheet.createRow(rowIdx++);
            row.createCell(0).setCellValue(emp.getId());
            row.createCell(1); 

            row.createCell(2).setCellValue(emp.getFullName());
            row.createCell(3);

            row.createCell(4).setCellValue(emp.getEmail());
            row.createCell(5);
            row.createCell(6); 

            row.createCell(7).setCellValue(emp.getPhone());
            row.createCell(8); 

            row.createCell(9).setCellValue(emp.getAddress());
            row.createCell(10); 

            row.createCell(11).setCellValue(emp.getJobTitle());
            row.createCell(12); 

            row.createCell(13).setCellValue(emp.getHireDate().toString());
            row.createCell(14); 

            row.createCell(15).setCellValue(emp.getSalary());
        }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }
}
