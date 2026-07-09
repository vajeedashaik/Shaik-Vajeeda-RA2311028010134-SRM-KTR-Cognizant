Exercise 7: MockMVC - Test get country service

Using MockMVC test the get country service. Create test cases to test the following aspects:
- Test if the CountryController is loaded
- Invoke the service to get country and check in the response if it contains code as "IN" and name as "India"

Refer steps below to implement:

Test loading CountryController:
1. Include CountryController instance variable in SpringLearnApplicationTests.java and autowire the instance variable using annotation.

```java
@Autowired
private CountryController countryController;
```

2. Include assertion in contextLoads() method to check if controller is loaded.

```java
@Test
public void contextLoads() {
    assertNotNull(countryController);
}
```

3. Run the JUnit testing by right clicking on SpringLearnApplicationTests.java > Run As > JUnit Test
4. This test can also be executed in command line using the following maven command in the root folder of the project (don't forget to include proxy details):

```
mvn clean test
```

5. Check if the log in the constructor of CountryController is called.

Test service to get the country:

1. Include below imports

```java
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
```

2. Include @AutoConfigureMockMvc annotation for SpringLearnApplicationTests.java
3. Autowire mock mvc in SpringLearnApplicationTests.java

```java
@Autowired
private MockMvc mvc;
```

4. Include a new test method in SpringLearnApplicationTests.java

```java
@Test
public void testGetCountry() throws Exception {
}
```

5. Include the following line in the new method that calls the service method. Execute the JUnit test and check if the test case is successful.

```java
@Test
public void testGetCountry() throws Exception {
    ResultActions actions = mvc.perform(get("/country"));
}
```

6. Include the following line to check if the HTTP Status is 200, which means the call is successful.

```java
actions.andExpect(status().isOk());
```

7. Include the following line to check if the code is available in the response

```java
actions.andExpect(jsonPath("$.code").exists());
```

8. Include the following line to check if the value of code is "IN"

```java
actions.andExpect(jsonPath("$.code").value("IN"));
```

9. Using above two steps include checks for "name" attribute and check if its value is "India"
