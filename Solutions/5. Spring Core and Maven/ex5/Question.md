Exercise 5: Configuring the Spring IoC Container

Scenario:
The library management application requires a central configuration for beans and dependencies.

Steps:
Create Spring Configuration File:
Create an XML configuration file named applicationContext.xml in the src/main/resources directory.
Define beans for BookService and BookRepository in the XML file.

Update the BookService Class:
Ensure that the BookService class has a setter method for BookRepository.

Run the Application:
Create a main class to load the Spring context and test the configuration.
