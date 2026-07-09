Exercise 7: Implementing Constructor and Setter Injection

Scenario:
The library management application requires both constructor and setter injection for better control over bean initialization.

Steps:
Configure Constructor Injection:
Update applicationContext.xml to configure constructor injection for BookService.

Configure Setter Injection:
Ensure that the BookService class has a setter method for BookRepository and configure it in applicationContext.xml.

Test the Injection:
Run the LibraryManagementApplication main class to verify both constructor and setter injection.
