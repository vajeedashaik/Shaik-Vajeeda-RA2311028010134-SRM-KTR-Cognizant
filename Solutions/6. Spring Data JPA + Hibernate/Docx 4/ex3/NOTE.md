Reference copy from the live project at Solutions/6. Spring Data JPA + Hibernate/EmployeeManagementSystem/src/main/java/com/cognizant/ems/primary/repository/. See ex1's NOTE.md for how to run the project.

Both repositories extend JpaRepository<T, Long>. DepartmentRepository adds one derived query method (findByNameIgnoreCase). EmployeeRepository's fuller set of derived/custom/named/paginated/projection query methods is introduced incrementally in ex5/ex6/ex8 — this snapshot shows the final version.
