package com.employeestechnicaltest.repository;

import com.employeestechnicaltest.model.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
      List<RoleEntity> findByRoleNameIn(List<String> roleNames);
}
