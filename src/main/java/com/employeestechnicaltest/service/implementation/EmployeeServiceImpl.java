package com.employeestechnicaltest.service.implementation;

import com.employeestechnicaltest.model.EmployeeEntity;
import com.employeestechnicaltest.model.QEmployeeEntity;
import com.employeestechnicaltest.repository.EmployeeRepository;
import com.employeestechnicaltest.service.IEmployeeService;
import com.querydsl.core.types.dsl.NumberExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeEntity saveEmployee(EmployeeEntity employeeEntity) {
        return employeeRepository.save(employeeEntity);
    }

    @Override
    public List<EmployeeEntity> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public EmployeeEntity getEmployeeById(Long userId) {
        return employeeRepository.findById(userId).orElse(null);
    }

    @Override
    public boolean deleteEmployeeById(Long userId) {
        return employeeRepository.findById(userId).map(employeeEntity -> {
            employeeRepository.delete(employeeEntity);
            return true;
        }).orElse(false);
    }

    @Override
    public List<EmployeeEntity> searchEmployeeByName(String name) {
        QEmployeeEntity qEmployeeEntity = QEmployeeEntity.employeeEntity;
        return (List<EmployeeEntity>) employeeRepository.findAll(
                qEmployeeEntity.name.likeIgnoreCase("%" + name + "%")
        );
    }

    @Override
    public EmployeeEntity getEmployeeWithMaxSalary() {
        QEmployeeEntity employee = QEmployeeEntity.employeeEntity;
       double maxSalary = employeeRepository.findAll().stream()
                .mapToDouble(EmployeeEntity::getSalary)
                .max()
                .orElse(0);

        return employeeRepository.findOne(employee.salary.eq(maxSalary)).orElse(null);
    }
}
