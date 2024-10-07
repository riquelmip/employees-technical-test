package com.employeestechnicaltest.service.implementation;

import com.employeestechnicaltest.model.EmployeeEntity;
import com.employeestechnicaltest.model.QEmployeeEntity;
import com.employeestechnicaltest.repository.EmployeeRepository;
import com.employeestechnicaltest.service.IEmployeeService;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
    private final JPAQueryFactory queryFactory;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EntityManager entityManager, EmployeeRepository employeeRepository) {
        this.queryFactory = new JPAQueryFactory(entityManager);
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeEntity saveEmployee(EmployeeEntity employeeEntity) {
        return employeeRepository.save(employeeEntity);
    }

    @Override
    public List<EmployeeEntity> getAllEmployees() {
        QEmployeeEntity entity = QEmployeeEntity.employeeEntity;
        return queryFactory.selectFrom(entity)
                .fetch();
    }

    @Override
    public EmployeeEntity getEmployeeById(Long userId) {
        QEmployeeEntity entity = QEmployeeEntity.employeeEntity;
        return queryFactory.selectFrom(entity)
                .where(entity.employeeId.eq(userId))
                .fetchOne();
    }

    @Override
    public boolean deleteEmployeeById(Long userId) {
        QEmployeeEntity entity = QEmployeeEntity.employeeEntity;
        return queryFactory.delete(entity)
                .where(entity.employeeId.eq(userId))
                .execute() == 1;
    }

    @Override
    public List<EmployeeEntity> searchEmployeeByName(String name) {
        QEmployeeEntity qEmployeeEntity = QEmployeeEntity.employeeEntity;
        return queryFactory.selectFrom(qEmployeeEntity)
                .where(qEmployeeEntity.name.containsIgnoreCase(name))
                .fetch();
    }

    @Override
    public EmployeeEntity getEmployeeWithMaxSalary() {
        QEmployeeEntity employee = QEmployeeEntity.employeeEntity;
        return queryFactory.selectFrom(employee)
                .where(employee.salary.eq(
                        queryFactory.select(employee.salary.max())
                                .from(employee)
                ))
                .fetchOne();
    }
}