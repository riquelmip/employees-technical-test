package com.employeestechnicaltest.controller;

import com.employeestechnicaltest.model.DepartmentEntity;
import com.employeestechnicaltest.model.UserEntity;
import com.employeestechnicaltest.model.dto.AuthCreateUserRequestDTO;
import com.employeestechnicaltest.service.implementation.DepartmentServiceImpl;
import com.employeestechnicaltest.service.implementation.UserServiceImpl;
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
@RequestMapping("/api/admin/departments")
public class DepartmentController {
    @Autowired
    private DepartmentServiceImpl departmentService;

    @PostMapping("/create")
    public ResponseEntity<ResponseObject> createDepartment(@RequestBody DepartmentEntity department) {
        try {
            DepartmentEntity departmentCreated = departmentService.saveDepartment(department);
            return ResponseObject.build(true, HttpStatus.CREATED, "Department created successfully", departmentCreated);
        } catch (Exception e) {
            return ResponseObject.build(false, HttpStatus.BAD_REQUEST, "Ocurró un error", e.getMessage());
        }
    }

    @PostMapping("/get-all")
    public ResponseEntity<ResponseObject> getAllDepartments() {
        try {
            return ResponseObject.build(true, HttpStatus.OK, "Departments retrieved successfully", departmentService.getAllDepartments());
        } catch (Exception e) {
            return ResponseObject.build(false, HttpStatus.BAD_REQUEST, "Ocurró un error", e.getMessage());
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<ResponseObject> deleteDepartment(@Param("id") Long id) {
        try {
            boolean deleted = departmentService.deleteDepartmentById(id);
            if (deleted) {
                return ResponseObject.build(true, HttpStatus.OK, "Department deleted successfully", null);
            } else {
                return ResponseObject.build(false, HttpStatus.NOT_FOUND, "Department not found", null);
            }
        } catch (Exception e) {
            return ResponseObject.build(false, HttpStatus.BAD_REQUEST, "Ocurró un error", e.getMessage());
        }
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseObject> updateDepartment(@RequestBody DepartmentEntity department) {
        try {
            if (department.getDepartmentId() == null || department.getDepartmentId() == 0) {
                return ResponseObject.build(false, HttpStatus.BAD_REQUEST, "Department id is required", null);
            } else if (departmentService.getDepartmentById(department.getDepartmentId()) == null) {
                return ResponseObject.build(false, HttpStatus.NOT_FOUND, "Department not found", null);
            }
            DepartmentEntity departmentUpdated = departmentService.saveDepartment(department);
            return ResponseObject.build(true, HttpStatus.OK, "Department updated successfully", departmentUpdated);
        } catch (Exception e) {
            return ResponseObject.build(false, HttpStatus.BAD_REQUEST, "Ocurró un error", e.getMessage());
        }
    }

    @PostMapping("/get-by-id")
    public ResponseEntity<ResponseObject> getDepartmentById(@Param("id") Long id) {
        try {
            DepartmentEntity department = departmentService.getDepartmentById(id);
            if (department != null) {
                return ResponseObject.build(true, HttpStatus.OK, "Department retrieved successfully", department);
            } else {
                return ResponseObject.build(false, HttpStatus.NOT_FOUND, "Department not found", null);
            }
        } catch (Exception e) {
            return ResponseObject.build(false, HttpStatus.BAD_REQUEST, "Ocurró un error", e.getMessage());
        }
    }

}
