package com.cognizant.ems.primary.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

// Docx 4 - Hands on 2: Employee entity, many-to-one owning side of the Department relationship
// Docx 4 - Hands on 5: named query, resolved by Spring Data JPA before it falls back to deriving
//                      a query from the method name
// Docx 4 - Hands on 10: @DynamicInsert/@DynamicUpdate are Hibernate-specific annotations that
//                       make generated INSERT/UPDATE statements only reference non-null columns
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "employee")
@DynamicInsert
@DynamicUpdate
@NamedQuery(
        name = "Employee.findByDepartmentId",
        query = "SELECT e FROM Employee e WHERE e.department.id = :departmentId"
)
public class Employee extends Auditable {

    // SEQUENCE (not IDENTITY) is required for Hibernate's JDBC batch inserts (Hands on 10) to
    // actually batch: IDENTITY forces one round-trip per row to read back the generated key.
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_seq")
    @SequenceGenerator(name = "employee_seq", sequenceName = "employee_seq", allocationSize = 20)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    public Employee(String name, String email, Department department) {
        this.name = name;
        this.email = email;
        this.department = department;
    }
}
