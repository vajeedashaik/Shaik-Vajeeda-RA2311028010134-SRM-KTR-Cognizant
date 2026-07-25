Reference copy from the live project at Solutions/6. Spring Data JPA + Hibernate/EmployeeManagementSystem/. See ex1's NOTE.md for how to run the project.

Auditable is a @MappedSuperclass carrying @CreatedDate/@LastModifiedDate/@CreatedBy/@LastModifiedBy, populated via Spring Data's AuditingEntityListener (@EntityListeners on Auditable). Employee extends it. JpaAuditingConfig enables auditing (@EnableJpaAuditing) and supplies the AuditorAware<String> bean (a fixed "system" auditor, since this project has no authentication layer to pull a real user from).

Verified: creating an employee populated createdDate/createdBy, and updating it advanced lastModifiedDate/lastModifiedBy.
