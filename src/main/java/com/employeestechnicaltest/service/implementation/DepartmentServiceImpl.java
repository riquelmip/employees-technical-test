package com.employeestechnicaltest.service.implementation;

import com.employeestechnicaltest.model.DepartmentEntity;
import com.employeestechnicaltest.model.EmployeeEntity;
import com.employeestechnicaltest.repository.DepartmentRepository;
import com.employeestechnicaltest.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class DepartmentServiceImpl implements IDepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentEntity saveDepartment(DepartmentEntity departmentEntity) {
        return departmentRepository.save(departmentEntity);
    }

    @Override
    public List<DepartmentEntity> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public List<EmployeeEntity> getEmployeesByDepartmentId(Long departmentId) {
        return Objects.requireNonNull(departmentRepository.findById(departmentId).orElse(null)).getEmployees();
    }

    @Override
    public boolean deleteDepartmentById(Long userId) {
        return departmentRepository.findById(userId).map(departmentEntity -> {
            departmentRepository.delete(departmentEntity);
            return true;
        }).orElse(false);
    }

    @Override
    public DepartmentEntity getDepartmentById(Long departmentId) {
        return departmentRepository.findById(departmentId).orElse(null);
    }
}
