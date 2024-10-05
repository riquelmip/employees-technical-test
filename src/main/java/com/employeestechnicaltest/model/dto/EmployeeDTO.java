package com.employeestechnicaltest.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDTO {
    private Long employeeId;
    private String name;
    private String lastname;
    private String direction;
    private double salary;
    private String phone;
    private Long departmentId;
}
