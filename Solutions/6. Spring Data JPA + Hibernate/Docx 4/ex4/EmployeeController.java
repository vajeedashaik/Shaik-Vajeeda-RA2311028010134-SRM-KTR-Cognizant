package com.cognizant.ems.primary.controller;

import com.cognizant.ems.primary.model.Employee;
import com.cognizant.ems.primary.projection.EmployeeDto;
import com.cognizant.ems.primary.projection.EmployeeSummary;
import com.cognizant.ems.primary.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Docx 4 - Hands on 6: e.g. GET /api/employees?page=0&size=10&sort=name,asc
    @GetMapping
    public Page<Employee> getAll(Pageable pageable) {
        return employeeService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable Long id) {
        return employeeService.findById(id);
    }

    @GetMapping("/search")
    public List<Employee> search(@RequestParam String name) {
        return employeeService.search(name);
    }

    @GetMapping("/by-department")
    public Page<Employee> byDepartment(@RequestParam String departmentName, Pageable pageable) {
        return employeeService.findByDepartmentName(departmentName, pageable);
    }

    // Docx 4 - Hands on 8: interface-based projection endpoint
    @GetMapping("/by-department/summary")
    public List<EmployeeSummary> summaryByDepartment(@RequestParam String departmentName) {
        return employeeService.summariesByDepartment(departmentName);
    }

    // Docx 4 - Hands on 8: class-based (DTO) projection endpoint
    @GetMapping("/by-department/dto")
    public List<EmployeeDto> dtoByDepartment(@RequestParam String departmentName) {
        return employeeService.dtosByDepartment(departmentName);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee create(@RequestBody Employee employee) {
        return employeeService.create(employee);
    }

    // Docx 4 - Hands on 10: bulk insert, exercises Hibernate JDBC batching
    @PostMapping("/bulk")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Employee> bulkCreate(@RequestBody List<Employee> employees) {
        return employeeService.bulkInsert(employees);
    }

    @PutMapping("/{id}")
    public Employee update(@PathVariable Long id, @RequestBody Employee employee) {
        return employeeService.update(id, employee);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        employeeService.delete(id);
    }
}
