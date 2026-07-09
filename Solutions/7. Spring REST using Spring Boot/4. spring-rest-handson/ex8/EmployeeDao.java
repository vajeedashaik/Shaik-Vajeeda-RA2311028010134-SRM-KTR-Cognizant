package com.cognizant.springlearn.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.cognizant.springlearn.Employee;
import com.cognizant.springlearn.service.exception.EmployeeNotFoundException;

// Handson 3 (file 3): Employee DAO - loads from XML config
@Repository
public class EmployeeDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDao.class);

    private static ArrayList<Employee> EMPLOYEE_LIST;

    public EmployeeDao() {
        LOGGER.info("START");
        ApplicationContext context = new ClassPathXmlApplicationContext("employee.xml");
        EMPLOYEE_LIST = context.getBean("employeeList", ArrayList.class);
        LOGGER.debug("Loaded {} employees", EMPLOYEE_LIST.size());
        LOGGER.info("END");
    }

    public List<Employee> getAllEmployees() {
        LOGGER.info("START");
        LOGGER.info("END");
        return EMPLOYEE_LIST;
    }

    public Employee getEmployee(Integer id) throws EmployeeNotFoundException {
        LOGGER.info("START");
        Employee emp = EMPLOYEE_LIST.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElseThrow(EmployeeNotFoundException::new);
        LOGGER.info("END");
        return emp;
    }

    // Handson 4 (file 4): update employee in list
    public void updateEmployee(Employee updated) throws EmployeeNotFoundException {
        LOGGER.info("START");
        Employee existing = getEmployee(updated.getId());
        existing.setName(updated.getName());
        existing.setSalary(updated.getSalary());
        existing.setPermanent(updated.getPermanent());
        existing.setDateOfBirth(updated.getDateOfBirth());
        existing.setDepartment(updated.getDepartment());
        existing.setSkills(updated.getSkills());
        LOGGER.debug("Updated employee: {}", existing);
        LOGGER.info("END");
    }

    // Handson 4 (file 4): delete employee from list
    public void deleteEmployee(Integer id) throws EmployeeNotFoundException {
        LOGGER.info("START");
        Employee emp = getEmployee(id);
        EMPLOYEE_LIST.remove(emp);
        LOGGER.debug("Deleted employee id: {}", id);
        LOGGER.info("END");
    }
}
