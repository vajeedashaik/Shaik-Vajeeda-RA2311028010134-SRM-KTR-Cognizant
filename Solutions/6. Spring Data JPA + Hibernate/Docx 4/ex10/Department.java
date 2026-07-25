package com.cognizant.ems.primary.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

// Docx 4 - Hands on 2: Department entity, one-to-many owning side is Employee.department
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "department_seq")
    @SequenceGenerator(name = "department_seq", sequenceName = "department_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String name;

    // Docx 4 - Hands on 10: @BatchSize is a Hibernate-specific annotation that reduces N+1 selects
    // when this collection is initialized for several Department instances in the same session
    // @JsonIgnore breaks the Employee.department <-> Department.employees cycle that would
    // otherwise recurse infinitely when Jackson serializes an Employee (or a Department) to JSON
    @JsonIgnore
    @org.hibernate.annotations.BatchSize(size = 20)
    @OneToMany(mappedBy = "department")
    private Set<Employee> employees = new HashSet<>();

    public Department(String name) {
        this.name = name;
    }
}
