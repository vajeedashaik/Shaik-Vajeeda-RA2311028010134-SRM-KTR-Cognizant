package com.cognizant.ems.primary.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

// Docx 4 - Hands on 7: enables @CreatedDate/@LastModifiedDate/@CreatedBy/@LastModifiedBy on Auditable.
// auditorAwareRef must name the primary entity manager factory's persistence unit implicitly via
// the AuditingEntityListener registered on that persistence context (there's only one @Entity
// hierarchy using auditing here, so no persistenceUnitTransactionManagerRef is needed).
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class JpaAuditingConfig {

    // Stands in for a real security-context lookup (e.g. Spring Security's
    // SecurityContextHolder.getContext().getAuthentication().getName()); this project has no
    // authentication layer, so a fixed "system" auditor is used.
    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> Optional.of("system");
    }
}
