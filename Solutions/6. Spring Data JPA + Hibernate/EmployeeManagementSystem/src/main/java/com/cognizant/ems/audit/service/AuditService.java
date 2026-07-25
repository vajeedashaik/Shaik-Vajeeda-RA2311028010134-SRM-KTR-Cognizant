package com.cognizant.ems.audit.service;

import com.cognizant.ems.audit.model.AuditEvent;
import com.cognizant.ems.audit.repository.AuditEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// Docx 4 - Hands on 9: writes to the audit datasource, independently of whatever transaction
// (if any) is active against the primary datasource. The two datasources are not part of a
// single distributed (XA) transaction, so a primary-side rollback will not undo an audit write
// that already committed - acceptable here since audit trails are meant to be append-only.
@Service
public class AuditService {

    @Autowired
    private AuditEventRepository auditEventRepository;

    @Transactional("auditTransactionManager")
    public void record(String entityName, String action, String detail) {
        auditEventRepository.save(new AuditEvent(entityName, action, detail));
    }
}
