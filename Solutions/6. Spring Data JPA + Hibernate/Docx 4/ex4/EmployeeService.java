package com.cognizant.ems.primary.service;

import com.cognizant.ems.audit.service.AuditService;
import com.cognizant.ems.primary.model.Employee;
import com.cognizant.ems.primary.projection.EmployeeDto;
import com.cognizant.ems.primary.projection.EmployeeSummary;
import com.cognizant.ems.primary.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AuditService auditService;

    @Transactional
    public Employee findById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Employee not found: " + id));
    }

    // Docx 4 - Hands on 6: pagination + sorting
    @Transactional
    public Page<Employee> findAll(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    @Transactional
    public Page<Employee> findByDepartmentName(String departmentName, Pageable pageable) {
        return employeeRepository.findByDepartmentName(departmentName, pageable);
    }

    @Transactional
    public List<Employee> search(String name) {
        return employeeRepository.findByNameContainingIgnoreCase(name);
    }

    // Docx 4 - Hands on 8: projections
    @Transactional
    public List<EmployeeSummary> summariesByDepartment(String departmentName) {
        return employeeRepository.findByDepartmentName(departmentName, EmployeeSummary.class);
    }

    @Transactional
    public List<EmployeeDto> dtosByDepartment(String departmentName) {
        return employeeRepository.findDtoByDepartmentName(departmentName);
    }

    @Transactional
    public Employee create(Employee employee) {
        Employee saved = employeeRepository.save(employee);
        auditService.record("Employee", "CREATE", "id=" + saved.getId() + ", email=" + saved.getEmail());
        return saved;
    }

    @Transactional
    public Employee update(Long id, Employee update) {
        Employee existing = findById(id);
        existing.setName(update.getName());
        existing.setEmail(update.getEmail());
        existing.setDepartment(update.getDepartment());
        Employee saved = employeeRepository.save(existing);
        auditService.record("Employee", "UPDATE", "id=" + saved.getId() + ", email=" + saved.getEmail());
        return saved;
    }

    @Transactional
    public void delete(Long id) {
        employeeRepository.deleteById(id);
        auditService.record("Employee", "DELETE", "id=" + id);
    }

    // Docx 4 - Hands on 10: bulk insert that benefits from the hibernate.jdbc.batch_size /
    // order_inserts settings in application.properties, now that Employee uses a SEQUENCE id
    // (see Employee.id) instead of IDENTITY
    @Transactional
    public List<Employee> bulkInsert(List<Employee> employees) {
        List<Employee> saved = employeeRepository.saveAll(employees);
        auditService.record("Employee", "BULK_CREATE", "count=" + saved.size());
        return saved;
    }
}
