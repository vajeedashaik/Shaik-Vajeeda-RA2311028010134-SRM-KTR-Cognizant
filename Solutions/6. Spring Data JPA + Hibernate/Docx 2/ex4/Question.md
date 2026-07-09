Hands on 4
Implement many to one relationship between Employee and Department Follow steps below to defined many to one relationship and perform persistence operations:Preparation of Service Classes
Create EmployeeService, DepartmentService and SkillService defined with annotation @Service. In each of this class autowire respective repository.
In each of the service class implement two methods one is to get the entity based on id and the other one is to save the entity. Sample code below provided for EmployeeService, in similar fashion include the methods for DepartmentService and SkillService.
EmployeeService - get() method
    @Transactional
    public Employee get(int id) {
        LOGGER.info("Start");
        return employeeRepository.findById(id).get();
    }
EmployeeService - save() method
    @Transactional
    public void save(Employee employee) {
        LOGGER.info("Start");
        employeeRepository.save(employee);
        LOGGER.info("End");
    }
Include static references of EmployeeService, DepartmentService and SkillService in OrmLearnApplication.
Assign employeeService, departmentService and skillService from the context in OrmLearnApplication main() method.
Implementation of @ManyToOne mapping
Define department in Employee bean with @ManyToOne and @JoinTable annotation. This defines the relationship between the entities.
    @ManyToOne
    @JoinColumn(name = "em_dp_id")
    private Department department;
Include setters and getters for instance variable department.
Getting Employee along with Department
Create new method testGetEmployee() in OrmLearnApplication
Implement below code in the method.
    private static void testGetEmployee() {
        LOGGER.info("Start");
        Employee employee = employeeService.get(1);
        LOGGER.debug("Employee:{}", employee);
        LOGGER.debug("Department:{}", employee.getDepartment());
        LOGGER.info("End");
    }
The above implementation gets the employee with id 1 and displays the employee details and department details.
Include testGetEmployee() method in main and comment the other test method calls.
Execute the main method and observe the following:
In the logs check the lines where the query is generated.
Since the relationship is defined, hibernate fetches department data as well. The query should look something like the below. Observe the department table join in this query. The query is formatted for better readability.
select employee0_.em_id as em_id1_2_0_, employee0_.em_date_of_birth as em_date_2_2_0_, 
       employee0_.em_dp_id as em_dp_id6_2_0_, employee0_.em_name as em_name3_2_0_, 
       employee0_.em_permanent as em_perma4_2_0_, employee0_.em_salary as em_salar5_2_0_, 
       department1_.dp_id as dp_id1_1_1_, department1_.dp_name as dp_name2_1_1_ 
  from employee employee0_ left outer join department department1_ 
    on employee0_.em_dp_id=department1_.dp_id 
 where employee0_.em_id=?
NOTE: SME to explain the learners about Eager Fetch and Lazy Fetch. As per JPA specification by default, Eager Fetch is applied For ManyToOne and OneToOne relationships. Hence department details as well is joined and fetched by Hibernate. Add Employee
Create new method testAddEmployee() in OrmLearnApplication and implement the following steps
Create a new instance of Employee
Set the values for the employee using setter method
Get a department based on department id 1 using departmentService
Set the department in employee based on the department obtained in the previous step
Invoke employeeService.save() passing the employee object created
Log employee object reference in debug mode
Include testAddEmployee() invocation in main() method and comment other test methods
Invoke the main method and check the following:
Log should contain select query to get the department and insert statement to add employee
Observe that the employee log after save contains the id. Hibernate inserts the records, gets the id and set the id instance variable of employee
Check in database if new employee data is inserted in employee table
Update Employee
Create new method testUpdateEmployee() in OrmLearnApplication and implement the following steps
Get an employee instance based on employee id using employeeService.get() method
Get a department based on department id using departmentService. Use a different department id from the one that is fetched.
Set the department in employee based on the department obtained in the previous step
Invoke employeeService.save() passing the employee object created
Log employee object reference in debug mode
Include testUpdateEmployee() invocation in main() method and comment other test methods
Invoke the main method and check the following:
Log should contain select query to get the department and employee. There should be update statement that updates employee table
Check in database if department id is modified.
