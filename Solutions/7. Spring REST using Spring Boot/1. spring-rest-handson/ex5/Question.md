Hands on 5: Spring Core – Demonstration of Singleton Scope and Prototype Scope

The Country bean done in the previous hands on will be used to demonstrate the scopes in Spring. Implement the steps below.

Follow steps below to demonstrate Singleton Scope:

1. Include a line in displayCountry() to get country bean reference one more time from the same application context. Only the third line of the below code snippet should be copied and pasted.

```java
ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
Country country = context.getBean("country", Country.class);
Country anotherCountry = context.getBean("country", Country.class);
```

2. The constructor will be called only once, which means that only one instance of Country bean is created

Follow steps below to demonstrate Prototype Scope:

1. Include scope="prototype" attribute in bean definition xml.

```xml
<bean id="country" class="com.cognizant.springlearn.Country" scope="prototype">
```

2. Run the application
3. Constructor will be called twice, which means that two instances of country is created.
