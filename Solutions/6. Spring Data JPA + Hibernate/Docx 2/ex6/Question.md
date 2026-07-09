Hands on 6
Implement many to many relationship between Employee and Skill Many to Many mapping defintion 
Include set of skill list in Employee.java with appropriate getter and setter
Include set of employee list in Skill.java with appropriate getter and setter
Include ManyToMany definition in Employee.java as specified below:
    @ManyToMany
    @JoinTable(name = "employee_skill",
        joinColumns = @JoinColumn(name = "es_em_id"), 
        inverseJoinColumns = @JoinColumn(name = "es_sk_id"))
    private Set<Skill> skillList;
Include ManyToMany defintion in Skill.java as specified below:
    @ManyToMany(mappedBy = "skillList")
    private Set<Employee> employeeList;
Fetching Employee along with Skills
In testGetEmployee() method of OrmLearnApplication.java include a new line to log employee skill details after the department log.
        LOGGER.debug("Skills:{}", employee.getSkillList());
Execute the main method which will fail with LazyInitializationException
Include fetch type as eager in @ManyToMany annotation of Employee.java, which will fetch the skill details as well.
Add Skill to Employee
Include a new method testAddSkillToEmployee() in OrmLearnApplication.java
Implement the following steps in this method:
Identify an employee id and skill id for which a relationship does not exists
Get employee based on employee id calling employeeService.get() method
Get skill based on skill id calling skillService.get() method
Get the skill list from employee and add the skill obtained in the previous step to the skill list
Call save method in employeeService passing the employee reference
Invoke testAddSkillToEmployee() in main method and comment other test methods
Execute the main method and check employee_skill table to verify if the skill is added to the employee.

