Reference copy from the live project at Solutions/6. Spring Data JPA + Hibernate/EmployeeManagementSystem/src/main/java/com/cognizant/ems/primary/model/. See ex1's NOTE.md for how to run the project.

Employee has id/name/email plus a @ManyToOne Department (department_id FK). Department has id/name plus the @OneToMany inverse side (mappedBy = "department"). Employee also extends Auditable (added in Hands on 7, included here since Employee depends on it to compile).
