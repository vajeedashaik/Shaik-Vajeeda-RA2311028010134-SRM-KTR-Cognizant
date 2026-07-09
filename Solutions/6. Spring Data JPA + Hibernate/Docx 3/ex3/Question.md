Hands on 3
Fetch quiz attempt details using HQL In a quiz application there is a requirement for admin to view details of a quiz that an user had attempted. This view should include the following details:
Username
Attempted Date
All questions as part of the attempt
List of options under each quiz
The option that is correct answer
The score for correct answer
Schema Diagram Notes on Schema:
Tables user, question and options are self explanatory. They hold the respective master data.
Tables attempt, attempt_question and attempt_option are used to hold the data of attempts made by each user.
Follow steps below to setup the schema:
Go to spring-data-jpa-files folder in windows explorer
Open file quiz.mwb in MySQL Workbench
Generate SQL file using File > Export > Forward Engineer SQL CREATE Script
Click Browse and select the file name and folder for the saving the generated SQL file
Select the check box "Generate INSERT Statements for Tables"
Click Next > Next > Finish to generate the SQL file
Execute the SQL file in ormlearn schema and check the data in the tables
Steps to get this implemented:
Create necessary entity class for each table defined above
Define necessary O/R mapping based on the schema defined above​​​
Create a Repository and Service class:
AttemptRepository
public Attempt getAttempt(int userId, int attemptId)
AttemptService
public Attempt getAttempt(int userId, int attemptId)
Modify OrmLearnApplication.java to include a new test method and test AttemptService.getAttemptDetail() method
Create HQL that joins the tables in the below order:
user
attempt
attempt_question
question
attempt_option
options
In the HQL include where class for userId and attemptId
Include 'fetch' in HQL wherever there is one-to-many or many-to-many relationship
In OrmLearnApplication.java test method ​​​​​​get the attempts details, iterate through the details and display the data in the following format. The second column in each option denotes the score from question table. The last column in each option denotes the answer selected by the user.
What is the extension of the hyper text markup language file?
 1) .xhtm       0.0     false
 2) .ht         0.0     false
 3) .html       1.0     true
 4) .htmx       0.0     false
What is the maximum level of heading tag can be used in a HTML page?
 1) 5           0.0     false
 2) 3           0.0     true
 3) 4           0.0     false
 4) 6           1.0     false
The HTML document itself begins with <html> and ends </html>. State True of False
 1) false        0.0    false
 2) true         1.0    true
Choose the right option to store text value value in a variable
 1) 'John'       0.5    true
 2) John         0.0    false
 3) "John"       0.5    false
 4) /John/       0.0    false
