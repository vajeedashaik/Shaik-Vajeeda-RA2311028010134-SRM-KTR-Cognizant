Reference copy from the live project at Solutions/6. Spring Data JPA + Hibernate/EmployeeManagementSystem/. See ex1's NOTE.md for how to run the project.

EmployeeRepository.findByNameContainingIgnoreCase / findByEmail are keyword-derived query methods. findByDepartmentName uses an explicit @Query. findByDepartmentId resolves against the @NamedQuery "Employee.findByDepartmentId" declared on Employee.java (Spring Data JPA's default lookup strategy checks for a matching named query, by convention EntityName.methodName, before falling back to deriving one from the method name).

Verified: GET /api/employees/search?name=ali and the department-scoped queries returned the expected rows against the running app.
