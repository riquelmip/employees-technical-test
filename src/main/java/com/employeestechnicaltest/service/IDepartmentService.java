package com.employeestechnicaltest.service;

import com.employeestechnicaltest.model.DepartmentEntity;
import com.employeestechnicaltest.model.EmployeeEntity;
import com.employeestechnicaltest.model.UserEntity;
import com.employeestechnicaltest.model.dto.AuthCreateUserRequestDTO;

import java.util.List;

public interface IDepartmentService {
    DepartmentEntity saveDepartment(DepartmentEntity userEntity);

    List<DepartmentEntity> getAllDepartments();

    List<EmployeeEntity> getEmployeesByDepartmentId(Long departmentId);

    boolean deleteDepartmentById(Long userId);

    DepartmentEntity getDepartmentById(Long departmentId);

}
