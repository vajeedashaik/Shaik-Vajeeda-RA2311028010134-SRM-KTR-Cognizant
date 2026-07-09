Exercise 6: Configuring Beans with Annotations

Scenario:
You need to simplify the configuration of beans in the library management application using annotations.

Steps:
Enable Component Scanning:
Update applicationContext.xml to include component scanning for the com.library package.

Annotate Classes:
Use @Service annotation for the BookService class.
Use @Repository annotation for the BookRepository class.

Test the Configuration:
Run the LibraryManagementApplication main class to verify the annotation-based configuration.
