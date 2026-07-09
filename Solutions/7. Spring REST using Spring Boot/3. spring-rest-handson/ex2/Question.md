Exercise 2: Create REST service for department

Create a new service to get all the departments.

Follow steps below to achieve this:

1. Create a new REST Service, define below list of classes and respective methods:
   - DepartmentController
     - getAllDepartments() with URL "/departments", this method will return array of departments
   - DepartmentService
     - getAllDepartments()
   - DepartmentDao
     - getAllDepartments() - Create a static variable DEPARTMENT_LIST, this should be populated from spring xml configuration
2. Test the service using postman.
3. Also verify if department REST service is called by looking into the logs.
