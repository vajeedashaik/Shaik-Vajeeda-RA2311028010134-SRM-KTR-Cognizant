Hands on 2
Get all permanent employees using HQL Using HQL get all permanent employees. When retrieving the employee details it should also retrieve respective department and skill list as well.HQL Solution
Include a new method definition in EmployeeRepository with @Query annotation
    @Query(value="SELECT e FROM Employee e WHERE e.permanent = 1")
    List<Employee> getAllPermanentEmployees();
    // NOTE: HQL looks like SQL, instead of table, Java classes and it's 
    // instance variables are addressed here
Include appropriate service method
Include a new test method and Invoke the service method in OrmLearnApplication.java. Refer test method below that logs all employee details and each employee's skill details.
    public static void testGetAllPermanentEmployees() {
        LOGGER.info("Start");
        List<Employee> employees = employeeService.getAllPermanentEmployees();
        LOGGER.debug("Permanent Employees:{}", employees);
        employees.forEach(e -> LOGGER.debug("Skills:{}", e.getSkillList()));
        LOGGER.info("End");
    }
Check the list of SQL queries executed in the log file. Following queries would have got executed.
select employee0_.em_id as em_id1_2_0_, department1_.dp_id as dp_id1_1_1_, 
       skill3_.sk_id as sk_id1_4_2_, employee0_.em_date_of_birth as em_date_2_2_0_, 
       employee0_.em_dp_id as em_dp_id6_2_0_, employee0_.em_name as em_name3_2_0_, 
       employee0_.em_permanent as em_perma4_2_0_, 
       employee0_.em_salary as em_salar5_2_0_, department1_.dp_name as dp_name2_1_1_, 
       skill3_.sk_name as sk_name2_4_2_, skilllist2_.es_em_id as es_em_id1_3_0__, 
       skilllist2_.es_sk_id as es_sk_id2_3_0__ 
from   employee employee0_ left outer join department department1_ on 
       employee0_.em_dp_id=department1_.dp_id left outer join employee_skill skilllist2_ on 
       employee0_.em_id=skilllist2_.es_em_id left outer join skill skill3_ on 
       skilllist2_.es_sk_id=skill3_.sk_id 
where  employee0_.em_permanent=1
select employeeli0_.em_dp_id as em_dp_id6_2_0_, employeeli0_.em_id as em_id1_2_0_, 
       employeeli0_.em_id as em_id1_2_1_, employeeli0_.em_date_of_birth as em_date_2_2_1_, 
       employeeli0_.em_dp_id as em_dp_id6_2_1_, employeeli0_.em_name as em_name3_2_1_, 
       employeeli0_.em_permanent as em_perma4_2_1_, employeeli0_.em_salary as em_salar5_2_1_ 
from   employee employeeli0_ where employeeli0_.em_dp_id=3
select employeeli0_.em_dp_id as em_dp_id6_2_0_, employeeli0_.em_id as em_id1_2_0_, 
       employeeli0_.em_id as em_id1_2_1_, employeeli0_.em_date_of_birth as em_date_2_2_1_, 
       employeeli0_.em_dp_id as em_dp_id6_2_1_, employeeli0_.em_name as em_name3_2_1_, 
       employeeli0_.em_permanent as em_perma4_2_1_, employeeli0_.em_salary as em_salar5_2_1_ 
from   employee employeeli0_ where employeeli0_.em_dp_id=2
select skilllist0_.es_em_id as es_em_id1_3_0_, skilllist0_.es_sk_id as es_sk_id2_3_0_, 
       skill1_.sk_id as sk_id1_4_1_, skill1_.sk_name as sk_name2_4_1_ 
from   employee_skill skilllist0_ inner join skill skill1_ 
on     skilllist0_.es_sk_id=skill1_.sk_id 
where  skilllist0_.es_em_id=2
Optimizing HQL Solution by removing the EAGER fetch configuration
An optimal solution should not execute multiple queries, we have defined unnecessary fetch configuration in
Eager fetch configuration is defined for employeeList in Department.java and skillList of Employee.java
Remove these two eager fetch configurations and check the logs. The following queries would have got executed. It would have failed when getting the skill list. Since we have remove the eager fetch skill is not retrieved.
select employee0_.em_id as em_id1_2_, employee0_.em_date_of_birth as em_date_2_2_, 
       employee0_.em_dp_id as em_dp_id6_2_, employee0_.em_name as em_name3_2_, 
       employee0_.em_permanent as em_perma4_2_, employee0_.em_salary as em_salar5_2_ 
from   employee employee0_ 
where  employee0_.em_permanent=1
select department0_.dp_id as dp_id1_1_0_, department0_.dp_name as dp_name2_1_0_ 
from   department department0_ 
where  department0_.dp_id=2
select department0_.dp_id as dp_id1_1_0_, department0_.dp_name as dp_name2_1_0_ 
from   department department0_ 
where  department0_.dp_id=3
There are two issues in this approach:
We did not get the skill details
Still the query is not optimal as we have three queries executed
Optimizing HQL solution by using 'fetch'
Change the query in EmployeeRepository.java as specified below:
@Query(value="SELECT e FROM Employee e left join e.department d left join e.skillList WHERE e.permanent = 1")
The above query still fails to get skill details. Include fetch after each join. Wherever data is required we can include fetch, which will populate the respective data. Change the query as specified below:
@Query(value="SELECT e FROM Employee e left join fetch e.department d left join fetch e.skillList WHERE e.permanent = 1")
Following the single query generated for the above HQL:
select employee0_.em_id as em_id1_2_0_, department1_.dp_id as dp_id1_1_1_, 
       skill3_.sk_id as sk_id1_4_2_, employee0_.em_date_of_birth as em_date_2_2_0_, 
       employee0_.em_dp_id as em_dp_id6_2_0_, employee0_.em_name as em_name3_2_0_, 
       employee0_.em_permanent as em_perma4_2_0_, employee0_.em_salary as em_salar5_2_0_, 
       department1_.dp_name as dp_name2_1_1_, skill3_.sk_name as sk_name2_4_2_, 
       skilllist2_.es_em_id as es_em_id1_3_0__, skilllist2_.es_sk_id as es_sk_id2_3_0__ 
from   employee employee0_ left outer join department department1_ on 
       employee0_.em_dp_id=department1_.dp_id left outer join employee_skill skilllist2_ on 
       employee0_.em_id=skilllist2_.es_em_id left outer join skill skill3_ on 
       skilllist2_.es_sk_id=skill3_.sk_id 
where  employee0_.em_permanent=1
IMPORTANT TAKEAWAY: Join keyword links the table, but does not populate the beans. Fetch ensures that the beans are populated. Based on our need wherever we need data, we can define fetch. When joining table data is not needed the fetch can be ignored.
