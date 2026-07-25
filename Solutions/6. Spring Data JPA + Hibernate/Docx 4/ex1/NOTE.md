Reference copy from the live project at Solutions/6. Spring Data JPA + Hibernate/EmployeeManagementSystem/. Unlike orm-learn (MySQL via Docker), this project uses an in-memory H2 database, so it needs no external database to run.

To run it: `cd "6. Spring Data JPA + Hibernate/EmployeeManagementSystem"` then `mvn spring-boot:run`. It starts on port 8080. The H2 console is available at /h2-console (jdbc:h2:mem:testdb, user sa, password password) while the app is running.

Files here: pom.xml (Spring Data JPA, Web, H2, Lombok dependencies), application.properties (H2 datasource config per this exercise's spec), EmployeeManagementSystemApplication.java (entry point).
