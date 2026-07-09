package com.cognizant.springlearn.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.springlearn.Employee;
import com.cognizant.springlearn.dao.EmployeeDao;
import com.cognizant.springlearn.service.exception.EmployeeNotFoundException;

// Handson 3 (file 3): Employee service
@Service
public class EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeDao employeeDao;

    @Transactional
    public List<Employee> getAllEmployees() {
        LOGGER.info("START");
        List<Employee> employees = employeeDao.getAllEmployees();
        LOGGER.info("END");
        return employees;
    }

    @Transactional
    public Employee getEmployee(Integer id) throws EmployeeNotFoundException {
        LOGGER.info("START");
        Employee employee = employeeDao.getEmployee(id);
        LOGGER.info("END");
        return employee;
    }

    @Transactional
    public void updateEmployee(Employee employee) throws EmployeeNotFoundException {
        LOGGER.info("START");
        employeeDao.updateEmployee(employee);
        LOGGER.info("END");
    }

    @Transactional
    public void deleteEmployee(Integer id) throws EmployeeNotFoundException {
        LOGGER.info("START");
        employeeDao.deleteEmployee(id);
        LOGGER.info("END");
    }
}
