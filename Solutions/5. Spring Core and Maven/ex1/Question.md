Exercise 1: Configuring a Basic Spring Application

Scenario:
Your company is developing a web application for managing a library. You need to use the Spring Framework to handle the backend operations.

Steps:
Set Up a Spring Project:
Create a Maven project named LibraryManagement.
Add Spring Core dependencies in the pom.xml file.

Configure the Application Context:
Create an XML configuration file named applicationContext.xml in the src/main/resources directory.
Define beans for BookService and BookRepository in the XML file.

Define Service and Repository Classes:
Create a package com.library.service and add a class BookService.
Create a package com.library.repository and add a class BookRepository.

Run the Application:
Create a main class to load the Spring context and test the configuration.
