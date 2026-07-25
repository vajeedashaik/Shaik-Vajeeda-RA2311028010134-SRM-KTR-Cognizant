package com.cognizant.ems.primary.repository;

import com.cognizant.ems.primary.model.Employee;
import com.cognizant.ems.primary.projection.EmployeeDto;
import com.cognizant.ems.primary.projection.EmployeeSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Docx 4 - Hands on 5: derived query methods (keyword-based)
    List<Employee> findByNameContainingIgnoreCase(String name);

    Optional<Employee> findByEmail(String email);

    // Docx 4 - Hands on 5: custom query via @Query
    @Query("SELECT e FROM Employee e WHERE e.department.name = :departmentName")
    List<Employee> findByDepartmentName(@Param("departmentName") String departmentName);

    // Docx 4 - Hands on 5: resolves the @NamedQuery "Employee.findByDepartmentId" declared on Employee
    List<Employee> findByDepartmentId(Long departmentId);

    // Docx 4 - Hands on 6: pagination + sorting (Pageable carries both the page/size and the Sort)
    Page<Employee> findByDepartmentName(String departmentName, Pageable pageable);

    Page<Employee> findAll(Pageable pageable);

    // Docx 4 - Hands on 8: interface-based (closed) projection
    List<EmployeeSummary> findByDepartmentName(String departmentName, Class<EmployeeSummary> type);

    // Docx 4 - Hands on 8: class-based (DTO) projection via constructor expression
    @Query("SELECT new com.cognizant.ems.primary.projection.EmployeeDto(e.id, e.name, e.department.name) " +
           "FROM Employee e WHERE e.department.name = :departmentName")
    List<EmployeeDto> findDtoByDepartmentName(@Param("departmentName") String departmentName);
}
