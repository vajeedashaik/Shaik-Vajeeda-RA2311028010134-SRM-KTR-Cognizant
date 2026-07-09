Exercise 1: Display Employee List using RESTful Web Service

Problem Statement: In the previous angular module, we developed a screen that lists employees and it was populated with hard coded values. Now this angular application has to be changed to get the data from RESTful Web Service developed in Spring. The following are the high level activities that need to be done to accomplish this:

1. Create static employee list data using spring xml configuration
2. Create a REST Service that reads data from xml configuration and returns it
3. Make changes in angular component to consume the created REST Service

Once above activities are completed, clicking on the Edit button against each employee should display Edit Employee form with values retrieved from RESTful Web Service. This will also involve activities similar to the one specified above. NOTE: There is no specific activity as part of this hands on, refer the next hands ons that cover above three activities in detail.

Create static employee list data using spring xml configuration. Follow steps below to accomplish this activity:

1. Incorporate the following in employee.xml:
   - Create one or two more departments
   - Create four more instances of Employee (use employee sample data from angular)
   - Reuse existing skills instead of creating new ones
   - Include all four employee instances in an ArrayList.
2. In EmployeeDao, incorporate the following:
   - Create static variable with name EMPLOYEE_LIST of type ArrayList<Employee>
   - Include constructor that reads employee list from xml config and sets the EMPLOYEE_LIST
   - Create method getAllEmployees() that returns the EMPLOYEE_LIST

Create REST service to get all employees. Follow steps below to accomplish this activity:

1. In EmployeeService, incorporate the following:
   - Change the annotation for this class from @Component to @Service
   - Create method getAllEmployees() that invokes employeeDao.getAllEmployees() and returns the employee list
   - Define @Transactional annotation for this method.
2. In EmployeeController, incorporate the following:
   - Include a new get method with name getAllEmployees() that returns the employee list
   - Mark this method as GetMapping annotation with the URL as '/employees'
   - Within this method invoke employeeService.getAllEmployees() and return the same.
3. Test the service using postman.
