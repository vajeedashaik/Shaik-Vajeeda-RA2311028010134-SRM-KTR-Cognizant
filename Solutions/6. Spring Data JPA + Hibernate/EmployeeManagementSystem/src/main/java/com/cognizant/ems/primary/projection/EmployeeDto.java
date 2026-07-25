package com.cognizant.ems.primary.projection;

import lombok.Value;

// Docx 4 - Hands on 8: class-based (DTO) projection, populated via a JPQL constructor expression
// (see EmployeeRepository.findDtoByDepartmentName) - @Value (Lombok) generates an all-args
// constructor plus getters, which is what the "new com.cognizant...EmployeeDto(...)" expression needs
@Value
public class EmployeeDto {
    Long id;
    String name;
    String departmentName;
}
