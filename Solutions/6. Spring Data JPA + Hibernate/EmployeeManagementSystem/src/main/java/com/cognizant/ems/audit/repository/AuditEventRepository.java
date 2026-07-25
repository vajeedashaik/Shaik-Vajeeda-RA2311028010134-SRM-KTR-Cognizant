package com.cognizant.ems.audit.repository;

import com.cognizant.ems.audit.model.AuditEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditEventRepository extends JpaRepository<AuditEvent, Long> {
}
