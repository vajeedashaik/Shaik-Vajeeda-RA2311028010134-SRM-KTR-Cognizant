Exercise 8: Implementing Basic AOP with Spring

Scenario:
The library management application requires basic AOP functionality to separate cross-cutting concerns like logging and transaction management.

Steps:
Define an Aspect:
Create a package com.library.aspect and add a class LoggingAspect.

Create Advice Methods:
Define advice methods in LoggingAspect for logging before and after method execution.

Configure the Aspect:
Update applicationContext.xml to register the aspect and enable AspectJ auto-proxying.

Test the Aspect:
Run the LibraryManagementApplication main class to verify the AOP functionality.
