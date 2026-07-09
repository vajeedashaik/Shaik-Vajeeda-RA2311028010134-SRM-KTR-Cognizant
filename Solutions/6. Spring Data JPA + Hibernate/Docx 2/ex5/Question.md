Hands on 5
Implement one to many relationship between Employee and Department Department.java
Include new instance variable for set of employees and define the OneToMay annotation
    @OneToMany(mappedBy = "department")
    private Set<Employee> employeeList;
Include setter and getter for employeeList
OrmLearnApplication.java
Include new method testGetDepartment()
In this method, get a department using departmentService.get() passing the id. Select an department id that has more than one employee.
Log the returned department and department.getEmployeeList()
Include testGetDepartment() method invocation in main method and comment the other test methods.
Execute the main() method which will fail with LazyInitializationException. This is because the default fetch type for OneToMany relationship is LAZY, hibernate fetches only department details and does not get the employee details.
In order to get the employee list as well, modify the annotation to include the fetch type as EAGER. Make this change in employeeList annotation definition of Department class.
    @OneToMany(mappedBy = "department", fetch = FetchType.EAGER)
    private Set<Employee> employeeList;
After this change try executing the main() method, which will fetch both department and employee
