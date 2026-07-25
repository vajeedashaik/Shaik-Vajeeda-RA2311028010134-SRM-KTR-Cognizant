package com.cognizant.ems.primary.service;

import com.cognizant.ems.audit.service.AuditService;
import com.cognizant.ems.primary.model.Department;
import com.cognizant.ems.primary.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private AuditService auditService;

    @Transactional
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Transactional
    public Department findById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Department not found: " + id));
    }

    @Transactional
    public Department create(Department department) {
        Department saved = departmentRepository.save(department);
        auditService.record("Department", "CREATE", "id=" + saved.getId() + ", name=" + saved.getName());
        return saved;
    }

    @Transactional
    public Department update(Long id, Department update) {
        Department existing = findById(id);
        existing.setName(update.getName());
        Department saved = departmentRepository.save(existing);
        auditService.record("Department", "UPDATE", "id=" + saved.getId() + ", name=" + saved.getName());
        return saved;
    }

    @Transactional
    public void delete(Long id) {
        departmentRepository.deleteById(id);
        auditService.record("Department", "DELETE", "id=" + id);
    }
}
