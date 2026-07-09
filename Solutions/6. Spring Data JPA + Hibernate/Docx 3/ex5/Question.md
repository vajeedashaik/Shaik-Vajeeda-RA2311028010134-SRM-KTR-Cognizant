Hands on 5
Get all employees using Native Query About Native Queries
Native queries are direct SQL queries to the database instead of using HQL
Try to avoid Native Queries and make it minimal.
Avoiding native queries helps in easier portability of database
Follow steps below to implement
Define a new native query method in EmployeeRepository
    @Query(value="SELECT * FROM employee", nativeQuery = true)
    List<Employee> getAllEmployeesNative();
Define relevant method in service and OrmLearnApplication and test it
