Hands on 2: Spring Core – Load SimpleDateFormat from Spring Configuration XML

SimpleDateFormat with the pattern 'dd/MM/yyyy' is created in multiple places of an application. To avoid creation of SimpleDateFormat in multiple places, define a bean in Spring XML Configuration file and retrieve the date.

Follow steps below to implement:

1. Create spring configuration file date-format.xml in src/main/resources folder of 'spring-learn' project
2. Open https://docs.spring.io/spring-framework/docs/current/spring-framework-reference/core.html#beans-factory-metadata
3. Copy the XML defined in the section of previous step URL and paste it into date-format.xml
4. Define bean tag in the XML with for date format. Refer code below.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">
    <bean id="dateFormat" class="java.text.SimpleDateFormat">
        <constructor-arg value="dd/MM/yyyy" />
    </bean>
</beans>
```

5. Create new method displayDate() in SpringLearnApplication.java
6. In displayDate() method create the ApplicationContext. Refer code below:

```java
ApplicationContext context = new ClassPathXmlApplicationContext("date-format.xml");
```

7. Get the dateFormat using getBean() method. Refer code below.

```java
SimpleDateFormat format = context.getBean("dateFormat", SimpleDateFormat.class);
```

8. Using the format variable try to parse string '31/12/2018' to Date class and display the result using System.out.println.
9. Run the application as 'Java Application' and check the result in console log output.

Troubleshooting Tips: If the tomcat port has a conflict and the server is not starting include the below property in application.properties file in src/main/resources folder.
