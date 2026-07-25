package com.cognizant.ems.primary.repository;

import com.cognizant.ems.primary.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Docx 4 - Hands on 3: repository for CRUD + a derived query method
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Optional<Department> findByNameIgnoreCase(String name);
}
