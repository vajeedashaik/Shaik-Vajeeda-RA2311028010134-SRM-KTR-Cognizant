Hands on 4
Get average salary using HQL Compute the average salary of a department using HQL.Refer steps below to implement:
Define HQL in EmployeeRepository
    @Query(value="SELECT AVG(e.salary) FROM Employee e")
    double getAverageSalary();
Include new method with above signature in EmployeeService and include test method in OrmLearnApplication
The above query does not filter the result based on department id. Modify the query and method signature as specified below to accept department.
@Query(value="SELECT AVG(e.salary) FROM Employee e where e.department.id = :id")
double getAverageSalary(@Param("id") int id);
NOTES:
Observe how department id is referred from 'e'
Make note of the colon (:) used to define a parameter within a query
@Param annotation helps in binding the input department id with the query parameter
Similar to AVG(), all other aggregate functions can be used
