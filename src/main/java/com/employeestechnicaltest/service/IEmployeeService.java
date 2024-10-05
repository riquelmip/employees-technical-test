package com.employeestechnicaltest.service;

import com.employeestechnicaltest.model.EmployeeEntity;

import java.util.List;

public interface IEmployeeService {
    EmployeeEntity saveEmployee(EmployeeEntity employeeEntity);

    List<EmployeeEntity> getAllEmployees();

    EmployeeEntity getEmployeeById(Long userId);

    boolean deleteEmployeeById(Long userId);

}
