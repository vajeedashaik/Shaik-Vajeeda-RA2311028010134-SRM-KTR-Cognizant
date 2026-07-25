Reference copy from the live project at Solutions/6. Spring Data JPA + Hibernate/EmployeeManagementSystem/. See ex1's NOTE.md for how to run the project.

EmployeeController.getAll(Pageable) and EmployeeController.byDepartment(...) accept page/size/sort as query parameters (Spring resolves them into a Pageable automatically). EmployeeRepository.findAll(Pageable) / findByDepartmentName(String, Pageable) return Page<Employee>, which carries content, totalElements, totalPages, sort, etc.

Verified: `GET /api/employees?page=0&size=2&sort=name,asc` returned a Page with 2 employees sorted alphabetically by name.
