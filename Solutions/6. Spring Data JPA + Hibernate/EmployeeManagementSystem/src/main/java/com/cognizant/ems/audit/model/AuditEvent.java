package com.cognizant.ems.audit.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

// Docx 4 - Hands on 9: lives in its own datasource (auditdb), separate from Employee/Department
// (testdb), to demonstrate managing multiple datasources in the same application
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "audit_event")
public class AuditEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String entityName;

    private String action;

    private String detail;

    private LocalDateTime occurredAt;

    public AuditEvent(String entityName, String action, String detail) {
        this.entityName = entityName;
        this.action = action;
        this.detail = detail;
        this.occurredAt = LocalDateTime.now();
    }
}
