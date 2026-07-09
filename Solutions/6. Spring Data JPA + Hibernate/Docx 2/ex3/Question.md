Hands on 3
Create payroll tables and bean mapping To demonstrate one to many, many to one and many to many relationships in Hibernate, a schema with entities employee, department and skill will be used. In this hands on we will setup the tables and data, which forms the basis for learning the mappings in Hibernate.Schema StructureFollow steps below to create necessary tables:
Open mysql client in command line
Use the source command to execute the payroll.sql script file available in spring-data-jpa-files folder. The following command assumes that spring-data-jpa-files folder is in D:.
 mysql> source D:\spring-data-jpa-files\payroll.sql
Define bean mapping
Open orm-learn project in Eclipse
Create model classes Employee, Department and Skill in com.cognizant.orm-learn.model package
Define each model should have @Entity and @Table annotations.
Each id field should be have @Id annotation and @GeneratedValue(strategy = GenerationType.IDENTITY) annotation. @GeneratedValue annotation ensures auto increment of id creation.
Define @Column against each field.
Define getters, setters and toString() methods
Employee
private int id;
private String name;
private double salary;
private boolean permanent;
private Date dateOfBirth;
Department
private int id;
private String name;
Skill
private int id;
private String name;
Create appropriate repository interfaces EmployeeRepository, DepartmentRepository and SkillRepository in repository package
