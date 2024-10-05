package com.employeestechnicaltest.controller;

import com.employeestechnicaltest.model.DepartmentEntity;
import com.employeestechnicaltest.model.EmployeeEntity;
import com.employeestechnicaltest.model.dto.EmployeeDTO;
import com.employeestechnicaltest.service.implementation.DepartmentServiceImpl;
import com.employeestechnicaltest.service.implementation.EmployeeServiceImpl;
import com.employeestechnicaltest.util.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/employees")
public class EmployeeController {
    @Autowired
    private EmployeeServiceImpl employeeService;
    @Autowired
    private DepartmentServiceImpl departmentService;

    @PostMapping("/create")
    public ResponseEntity<ResponseObject> create(@RequestBody EmployeeDTO employee) {
        try {
            DepartmentEntity department = departmentService.getDepartmentById(employee.getDepartmentId());
            if (department == null) {
                return ResponseObject.build(false, HttpStatus.NOT_FOUND, "Department not found", null);
            }

            EmployeeEntity employeeEntity = EmployeeEntity.builder()
                    .name(employee.getName())
                    .lastname(employee.getLastname())
                    .direction(employee.getDirection())
                    .salary(employee.getSalary())
                    .phone(employee.getPhone())
                    .department(department)
                    .build();

            EmployeeEntity employeeCreated = employeeService.saveEmployee(employeeEntity);
            return ResponseObject.build(true, HttpStatus.CREATED, "Employee created successfully", employeeCreated);
        } catch (Exception e) {
            return ResponseObject.build(false, HttpStatus.BAD_REQUEST, "Ocurró un error", e.getMessage());
        }
    }

    @PostMapping("/get-all")
    public ResponseEntity<ResponseObject> getAll() {
        try {
            return ResponseObject.build(true, HttpStatus.OK, "Employees retrieved successfully", employeeService.getAllEmployees());
        } catch (Exception e) {
            return ResponseObject.build(false, HttpStatus.BAD_REQUEST, "Ocurró un error", e.getMessage());
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<ResponseObject> delete(@Param("id") Long id) {
        try {
            boolean deleted = employeeService.deleteEmployeeById(id);
            if (deleted) {
                return ResponseObject.build(true, HttpStatus.OK, "Employee deleted successfully", null);
            } else {
                return ResponseObject.build(false, HttpStatus.NOT_FOUND, "Employee not found", null);
            }
        } catch (Exception e) {
            return ResponseObject.build(false, HttpStatus.BAD_REQUEST, "Ocurró un error", e.getMessage());
        }
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseObject> update(@RequestBody EmployeeDTO employee) {
        try {
            DepartmentEntity department = departmentService.getDepartmentById(employee.getDepartmentId());
            if (department == null) {
                return ResponseObject.build(false, HttpStatus.NOT_FOUND, "Department not found", null);
            }

            if (employee.getEmployeeId() == null) {
                return ResponseObject.build(false, HttpStatus.BAD_REQUEST, "Employee ID is required", null);
            } else if (employeeService.getEmployeeById(employee.getEmployeeId()) == null) {
                return ResponseObject.build(false, HttpStatus.NOT_FOUND, "Employee not found", null);
            }

            EmployeeEntity employeeEntity = EmployeeEntity.builder()
                    .employeeId(employee.getEmployeeId())
                    .name(employee.getName())
                    .lastname(employee.getLastname())
                    .direction(employee.getDirection())
                    .salary(employee.getSalary())
                    .phone(employee.getPhone())
                    .department(department)
                    .build();

            EmployeeEntity employeeUpdated = employeeService.saveEmployee(employeeEntity);
            return ResponseObject.build(true, HttpStatus.OK, "Employee updated successfully", employeeUpdated);
        } catch (Exception e) {
            return ResponseObject.build(false, HttpStatus.BAD_REQUEST, "Ocurró un error", e.getMessage());
        }
    }

    @PostMapping("/get-by-id")
    public ResponseEntity<ResponseObject> getDepartmentById(@Param("id") Long id) {
        try {
            EmployeeEntity employee = employeeService.getEmployeeById(id);
            if (employee != null) {
                return ResponseObject.build(true, HttpStatus.OK, "Employee retrieved successfully", employee);
            } else {
                return ResponseObject.build(false, HttpStatus.NOT_FOUND, "Employee not found", null);
            }
        } catch (Exception e) {
            return ResponseObject.build(false, HttpStatus.BAD_REQUEST, "Ocurró un error", e.getMessage());
        }
    }

}
