package com.cognizant.springlearn.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.springlearn.Employee;
import com.cognizant.springlearn.service.EmployeeService;
import com.cognizant.springlearn.service.exception.EmployeeNotFoundException;

// Handson 3 (file 3): Employee REST services (GET)
// Handson 4 (file 4): PUT/DELETE with validation
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    public EmployeeController() {
        LOGGER.debug("Inside EmployeeController constructor.");
    }

    // GET /employees - get all employees
    @GetMapping
    public List<Employee> getAllEmployees() {
        LOGGER.info("START");
        List<Employee> employees = employeeService.getAllEmployees();
        LOGGER.info("END");
        return employees;
    }

    // GET /employees/{id} - get specific employee
    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable Integer id) throws EmployeeNotFoundException {
        LOGGER.info("START");
        Employee employee = employeeService.getEmployee(id);
        LOGGER.info("END");
        return employee;
    }

    // PUT /employees - update employee (Handson 4)
    @PutMapping
    public void updateEmployee(@RequestBody @Valid Employee employee) throws EmployeeNotFoundException {
        LOGGER.info("START");
        LOGGER.debug("Employee: {}", employee);
        employeeService.updateEmployee(employee);
        LOGGER.info("END");
    }

    // DELETE /employees/{id} - delete employee (Handson 4)
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Integer id) throws EmployeeNotFoundException {
        LOGGER.info("START");
        employeeService.deleteEmployee(id);
        LOGGER.info("END");
    }
}
