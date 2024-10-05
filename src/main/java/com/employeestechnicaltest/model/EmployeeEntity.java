package com.employeestechnicaltest.model;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "name")
    private String name;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "direction")
    private String direction;

    @Column(name = "salary")
    private double salary;

    @Column(name = "phone")
    private String phone;

    @ManyToOne
    @JoinColumn(name = "fk_department_id")
    private DepartmentEntity department;
}
