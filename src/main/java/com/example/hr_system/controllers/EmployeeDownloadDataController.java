package com.example.hr_system.controllers;

import java.io.ByteArrayInputStream;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hr_system.services.EmployeeExportService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
@Slf4j
public class EmployeeDownloadDataController {

    private final @Qualifier("excelExportService") EmployeeExportService employeeExportService;


    // Export all employees data to Excel file
    @GetMapping("/export/excel")
    public ResponseEntity<InputStreamResource> exportEmployeesToExcel() {
        log.info("Received request to export employees data to Excel");

        try {
            ByteArrayInputStream in = employeeExportService.exportEmployees();

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=employees.xlsx");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                    .body(new InputStreamResource(in));
        } catch (Exception e) {
            log.error("Failed to export employees to Excel", e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
