package com.cognizant.ems.primary.projection;

// Docx 4 - Hands on 8: interface-based (closed) projection - Spring Data generates the
// implementation and the backing SQL only selects the properties referenced here
public interface EmployeeSummary {

    String getName();

    String getEmail();

    DepartmentSummary getDepartment();

    interface DepartmentSummary {
        String getName();
    }
}
