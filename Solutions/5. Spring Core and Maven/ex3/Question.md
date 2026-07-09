Exercise 3: Implementing Logging with Spring AOP

Scenario:
The library management application requires logging capabilities to track method execution times.

Steps:
Add Spring AOP Dependency:
Update pom.xml to include Spring AOP dependency.

Create an Aspect for Logging:
Create a package com.library.aspect and add a class LoggingAspect with a method to log execution times.

Enable AspectJ Support:
Update applicationContext.xml to enable AspectJ support and register the aspect.

Test the Aspect:
Run the LibraryManagementApplication main class and observe the console for log messages indicating method execution times.
