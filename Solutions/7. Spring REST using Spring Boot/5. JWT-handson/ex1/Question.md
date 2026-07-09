Exercise 1: Securing RESTful Web Services with Spring Security

Follow steps below to secure all web services using Spring Security:

1. Open spring-learn project in Eclipse
2. Include spring security related libraries by adding the below dependency in pom.xml

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

3. Rebuild the project in command line using mvn clean package command (ensure to include proxy details in mvn command).
4. To ensure the new libraries are enabled in Eclipse, right click the project and select Maven > Update Project
5. Create a new package 'com.cognizant.spring-learn.security'
6. Create a new class SecurityConfig in the new package created above which extends from WebSecurityConfigurerAdapter
7. Include annotations @Configuration and @EnableWebSecurity at class level
8. Import appropriate classes using Ctrl + Shift + O
9. Start the application and check the logs and test the REST service. Refer command below:

```
curl -s http://localhost:8090/countries
```

The following error message is the expected response:
```
{"timestamp":"2019-10-05T09:24:33.794+0000","status":401,"error":"Unauthorized","message":"Unauthorized","path":"/countries"}
```

The inclusion of @EnableWebSecurity has restricted access to all the web services with a common password.

10. Refer the logs to find out the password generated. Now execute the invocation of the service with password as specified below, which should get the list of countries. Include the password from the log file after user:.

```
curl -s -v -u user:d27321a9-0751-4f59-8fc6-f8633847a9b8 http://localhost:8090/countries
```

Find below a sample response for the above command showing the request/response headers, including the `Authorization: Basic ...` header. This denotes that it uses basic HTTP Authorization — whatever follows "Basic" is Base64 encoding of the password that was supplied in the command line.
