Reference copy from the live project at Solutions/6. Spring Data JPA + Hibernate/EmployeeManagementSystem/. See ex1's NOTE.md for how to run the project.

EmployeeController/DepartmentController expose CRUD over EmployeeService/DepartmentService, which wrap the JpaRepository methods. RestExceptionHandler maps a not-found lookup to HTTP 404 instead of a generic 500.

Verified manually against the running app (mvn spring-boot:run, port 8080):
- POST /api/departments {"name":"Engineering"} -> 201
- POST /api/employees {"name":"Alice","email":"alice@example.com","department":{"id":1}} -> 201
- GET /api/employees/1 -> 200 with department populated
- PUT /api/employees/1 -> 200, lastModifiedDate advances
- DELETE /api/employees/{id} -> 204, subsequent GET -> 404
