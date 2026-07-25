Reference copy from the live project at Solutions/6. Spring Data JPA + Hibernate/EmployeeManagementSystem/. See ex1's NOTE.md for how to run the project.

EmployeeSummary is an interface-based (closed) projection with a nested DepartmentSummary — Spring Data generates the implementation and narrows the underlying SELECT to just the referenced columns. EmployeeDto is a class-based projection populated via a JPQL constructor expression (EmployeeRepository.findDtoByDepartmentName); it's a Lombok @Value class so the all-args constructor the query needs is generated automatically.

Verified: GET /api/employees/by-department/summary and .../dto both returned only the projected fields, and the generated SQL (visible via spring.jpa.show-sql=true) only selected those columns.
