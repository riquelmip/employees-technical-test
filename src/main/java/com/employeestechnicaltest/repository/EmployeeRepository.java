package com.employeestechnicaltest.repository;

import com.employeestechnicaltest.model.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long>, QuerydslPredicateExecutor<EmployeeEntity> {
}
