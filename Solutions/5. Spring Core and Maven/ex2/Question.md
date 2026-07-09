Exercise 2: Implementing Dependency Injection

Scenario:
In the library management application, you need to manage the dependencies between the BookService and BookRepository classes using Spring's IoC and DI.

Steps:
Modify the XML Configuration:
Update applicationContext.xml to wire BookRepository into BookService.

Update the BookService Class:
Ensure that BookService class has a setter method for BookRepository.

Test the Configuration:
Run the LibraryManagementApplication main class to verify the dependency injection.
