JUnit Testing Exercises
Exercise 1: Setting Up JUnit
Scenario:
You need to set up JUnit in your Java project to start writing unit tests.
Steps:
1. Create a new Java project in your IDE (e.g., IntelliJ IDEA, Eclipse).
2. Add JUnit dependency to your project. If you are using Maven, add the following to your
pom.xml:
<dependency>
 <groupId>junit</groupId>
 <artifactId>junit</artifactId>
 <version>4.13.2</version>
 <scope>test</scope>
</dependency>
3. Create a new test class in your project.
Exercise 2: Writing Basic JUnit Tests
Scenario:
You need to write basic JUnit tests for a simple Java class.
Steps:
1. Create a new Java class with some methods to test.
2. Write JUnit tests for these methods.
Exercise 3: Assertions in JUnit
Scenario:
You need to use different assertions in JUnit to validate your test results.
Steps:
1. Write tests using various JUnit assertions.
Solution Code:
public class AssertionsTest {
 @Test
 public void testAssertions() {
 // Assert equals
 assertEquals(5, 2 + 3);
 // Assert true
 assertTrue(5 > 3);
 // Assert false
 assertFalse(5 < 3);
 // Assert null
 assertNull(null);
 // Assert not null
 assertNotNull(new Object());
 }
}
Exercise 4: Arrange-Act-Assert (AAA) Pattern, Test Fixtures, Setup and
Teardown Methods in JUnit
Scenario:
You need to organize your tests using the Arrange-Act-Assert (AAA) pattern and use setup
and teardown methods.
Steps:
1. Write tests using the AAA pattern.
2. Use @Before and @After annotations for setup and teardown methods. 

Advanced JUnit Testing Exercises
Exercise 1: Parameterized Tests
Scenario:
You want to test a method that checks if a number is even. Instead of writing multiple test
cases, you will use parameterized tests to run the same test with different inputs.
Steps:
1. Create a new Java class `EvenChecker` with a method `isEven(int number)`.
2. Write a parameterized test class `EvenCheckerTest` that tests the `isEven` method with
different inputs.
3. Use JUnit's `@ParameterizedTest` and `@ValueSource` annotations.
Exercise 2: Test Suites and Categories
Scenario:
You want to group related tests into a test suite and categorize them.
Steps:
1. Create a new test suite class `AllTests`.
2. Add multiple test classes to the suite.
3. Use JUnit's `@Suite` and `@SelectClasses` annotations.
Exercise 3: Test Execution Order
Scenario:
You want to control the order in which tests are executed.
Steps:
1. Create a test class `OrderedTests`.
2. Use JUnit's `@TestMethodOrder` and `@Order` annotations.
Exercise 4: Exception Testing
Scenario:
You want to test that a method throws the expected exception.
Steps:
1. Create a class `ExceptionThrower` with a method `throwException`.
2. Write a test class `ExceptionThrowerTest` that tests the method for the expected
exception.
Exercise 5: Timeout and Performance Testing
Scenario:
You want to ensure that a method completes within a specified time limit.
Steps:
1. Create a class `PerformanceTester` with a method `performTask`.
2. Write a test class `PerformanceTesterTest` that tests the method for timeout. 

Mockito Hands-On Exercises
Exercise 1: Mocking and Stubbing
Scenario:
You need to test a service that depends on an external API. Use Mockito to mock the
external API and stub its methods.
Steps:
1. Create a mock object for the external API.
2. Stub the methods to return predefined values.
3. Write a test case that uses the mock object.
Solution Code:
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
public class MyServiceTest {
 @Test
 public void testExternalApi() {
 ExternalApi mockApi = Mockito.mock(ExternalApi.class);
 when(mockApi.getData()).thenReturn("Mock Data");
 MyService service = new MyService(mockApi);
 String result = service.fetchData();
 assertEquals("Mock Data", result);
 }
}
Exercise 2: Verifying Interactions
Scenario:
You need to ensure that a method is called with specific arguments.
Steps:
1. Create a mock object.
2. Call the method with specific arguments.
3. Verify the interaction.
Solution Code:
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
public class MyServiceTest {
 @Test
 public void testVerifyInteraction() {
 ExternalApi mockApi = Mockito.mock(ExternalApi.class);
 MyService service = new MyService(mockApi);
 service.fetchData();
 verify(mockApi).getData();
 }
}
Exercise 3: Argument Matching
Scenario:
You need to verify that a method is called with specific arguments.
Steps:
1. Create a mock object.
2. Call the method with specific arguments.
3. Use argument matchers to verify the interaction.
Exercise 4: Handling Void Methods
Scenario:
You need to test a void method that performs some action.
Steps:
1. Create a mock object.
2. Stub the void method.
3. Verify the interaction.
Exercise 5: Mocking and Stubbing with Multiple Returns
Scenario:
You need to test a service that depends on an external API with multiple return values.
Steps:
1. Create a mock object for the external API.
2. Stub the methods to return different values on consecutive calls.
3. Write a test case that uses the mock object.
Exercise 6: Verifying Interaction Order
Scenario:
You need to ensure that methods are called in a specific order.
Steps:
1. Create a mock object.
2. Call the methods in a specific order.
3. Verify the interaction order.
Exercise 7: Handling Void Methods with Exceptions
Scenario:
You need to test a void method that throws an exception.
Steps:
1. Create a mock object.
2. Stub the void method to throw an exception.
3. Verify the interaction. 

Advanced Mockito Hands-On Exercises
Solution to the exercises are given. Please go through them and try them yourself!!
Exercise 1: Mocking Databases and Repositories
You need to test a service that interacts with a database repository.
Steps:
1. Create a mock repository using Mockito.
2. Stub the repository methods to return predefined data.
3. Write a test to verify the service logic using the mocked repository.
Solution Code:
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class ServiceTest {
 @Test
 public void testServiceWithMockRepository() {
 Repository mockRepository = mock(Repository.class);
 when(mockRepository.getData()).thenReturn("Mock Data");
 Service service = new Service(mockRepository);
 String result = service.processData();
 assertEquals("Processed Mock Data", result);
 }
}
Exercise 2: Mocking External Services (RESTful APIs)
You need to test a service that calls an external RESTful API.
Steps:
1. Create a mock REST client using Mockito.
2. Stub the REST client methods to return predefined responses.
3. Write a test to verify the service logic using the mocked REST client.
Solution Code:
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class ApiServiceTest {
 @Test
 public void testServiceWithMockRestClient() {
 RestClient mockRestClient = mock(RestClient.class);
 when(mockRestClient.getResponse()).thenReturn("Mock Response");
 ApiService apiService = new ApiService(mockRestClient);
 String result = apiService.fetchData();
 assertEquals("Fetched Mock Response", result);
 }
}
Exercise 3: Mocking File I/O
You need to test a service that reads from and writes to files.
Steps:
1. Create a mock file reader and writer using Mockito.
2. Stub the file reader and writer methods to simulate file operations.
3. Write a test to verify the service logic using the mocked file reader and writer.
Solution Code:
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class FileServiceTest {
 @Test
 public void testServiceWithMockFileIO() {
 FileReader mockFileReader = mock(FileReader.class);
 FileWriter mockFileWriter = mock(FileWriter.class);
 when(mockFileReader.read()).thenReturn("Mock File Content");
 FileService fileService = new FileService(mockFileReader, mockFileWriter);
 String result = fileService.processFile();
 assertEquals("Processed Mock File Content", result);
 }
}
Exercise 4: Mocking Network Interactions
You need to test a service that interacts with network resources.
Steps:
4. 1. Create a mock network client using Mockito.
5. 2. Stub the network client methods to simulate network interactions.
6. 3. Write a test to verify the service logic using the mocked network client.
Solution Code:
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class NetworkServiceTest {
 @Test
 public void testServiceWithMockNetworkClient() {
 NetworkClient mockNetworkClient = mock(NetworkClient.class);
 when(mockNetworkClient.connect()).thenReturn("Mock Connection");
 NetworkService networkService = new NetworkService(mockNetworkClient);
 String result = networkService.connectToServer();
 assertEquals("Connected to Mock Connection", result);
 }
}
Exercise 5: Mocking Multiple Return Values
You need to test a service that calls a method multiple times with
different return values.
Steps:
1. Create a mock object using Mockito.
2. Stub the method to return different values on consecutive calls.
3. Write a test to verify the service logic using the mocked object.
Solution Code:
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class MultiReturnServiceTest {
 @Test
 public void testServiceWithMultipleReturnValues() {
 Repository mockRepository = mock(Repository.class);
 when(mockRepository.getData())
 .thenReturn("First Mock Data")
 .thenReturn("Second Mock Data");
 Service service = new Service(mockRepository);
 String firstResult = service.processData();
 String secondResult = service.processData();
 assertEquals("Processed First Mock Data", firstResult);
 assertEquals("Processed Second Mock Data", secondResult);
 }
} 

Spring Testing Exercises
Exercise 1: Basic Unit Test for a Service Method
Task: Write a unit test for a service method that adds two numbers.
Service:
@Service
public class CalculatorService {
 public int add(int a, int b) {
 return a + b;
 }
}
Test:
Write code for this.
Exercise 2: Mocking a Repository in a Service Test
Task: Test a service that uses a repository to fetch data.
Entity:
@Entity
public class User {
 @Id
 private Long id;
 private String name;
 // getters and setters
}
Repository:
public interface UserRepository extends JpaRepository<User, Long> {
}
Service:
@Service
public class UserService {
 @Autowired
 private UserRepository userRepository;
 public User getUserById(Long id) {
 return userRepository.findById(id).orElse(null);
 }
}
Test:
Write code for this.
Exercise 3: Testing a REST Controller with MockMvc
Task: Test a controller endpoint that returns a user.
Controller:
@RestController
@RequestMapping("/users")
public class UserController {
 @Autowired
 private UserService userService;
 @GetMapping("/{id}")
 public ResponseEntity<User> getUser(@PathVariable Long id) {
 return ResponseEntity.ok(userService.getUserById(id));
 }
}
Test:
Write code for this.
Exercise 4: Integration Test with Spring Boot
Task: Write an integration test that tests the full flow from controller to database.
Test:
Write code for this.
Exercise 5: Test Controller POST Endpoint
Task: Test a POST endpoint that creates a user.
Controller:
@PostMapping
public ResponseEntity<User> createUser(@RequestBody User user) {
 return ResponseEntity.ok(userService.saveUser(user));
}
Test:
Write code for this.
Exercise 6: Test Service Exception Handling
Task: Test how a service handles a missing user.
Test:
Write code for this.
Exercise 7: Test Custom Repository Query
Task: Add and test a custom query method.
Repository:
public interface UserRepository extends JpaRepository<User, Long> {
 List<User> findByName(String name);
}
Test:
Write code for this.
Exercise 8: Test Controller Exception Handling
Task: Add and test a @ControllerAdvice for handling exceptions.
Exception Handler:
@ControllerAdvice
public class GlobalExceptionHandler {
 @ExceptionHandler(NoSuchElementException.class)
 public ResponseEntity<String> handleNotFound(NoSuchElementException ex) {
 return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
 }
}
Test:
Write code for this.
Exercise 9: Parameterized Test with JUnit
Task: Use @ParameterizedTest to test multiple inputs.
Test:
Write code for this. 

Mocking Dependencies in Spring Tests
using Mockito
Exercise 1: Mocking a Service Dependency in a Controller Test
**Task:** Write a unit test for a Spring controller that uses a service to fetch data. Mock the
service dependency using Mockito.
### Step-by-Step Solution:
1. **Create the User Entity:**
@Entity
public class User {
 @Id
 private Long id;
 private String name;
 // getters and setters
}
2. **Create the UserService:**
@Service
public class UserService {
 @Autowired
 private UserRepository userRepository;
 public User getUserById(Long id) {
 return userRepository.findById(id).orElse(null);
 }
}
3. **Create the UserController:**
@RestController
@RequestMapping("/users")
public class UserController {
 @Autowired
 private UserService userService;
 @GetMapping("/{id}")
 public ResponseEntity<User> getUser(@PathVariable Long id) {
 return ResponseEntity.ok(userService.getUserById(id));
 }
}
4. **Create the UserControllerTest:**
Write code for this.
Exercise 2: Mocking a Repository in a Service Test
**Task:** Write a unit test for a Spring service that uses a repository to fetch data. Mock the
repository dependency using Mockito.
### Step-by-Step Solution:
1. **Create the User Entity:**
@Entity
public class User {
 @Id
 private Long id;
 private String name;
 // getters and setters
}
2. **Create the UserRepository:**
public interface UserRepository extends JpaRepository<User, Long> {
}
3. **Create the UserService:**
@Service
public class UserService {
 @Autowired
 private UserRepository userRepository;
 public User getUserById(Long id) {
 return userRepository.findById(id).orElse(null);
 }
}
4. **Create the UserServiceTest:**
Write code for this.
Exercise 3: Mocking a Service Dependency in an Integration Test
**Task:** Write an integration test for a Spring Boot application that mocks a service
dependency using Mockito.
### Step-by-Step Solution:
1. **Create the User Entity:**
@Entity
public class User {
 @Id
 private Long id;
 private String name;
 // getters and setters
}
2. **Create the UserService:**
@Service
public class UserService {
 @Autowired
 private UserRepository userRepository;
 public User getUserById(Long id) {
 return userRepository.findById(id).orElse(null);
 }
}
3. **Create the UserController:**
@RestController
@RequestMapping("/users")
public class UserController {
 @Autowired
 private UserService userService;
 @GetMapping("/{id}")
 public ResponseEntity<User> getUser(@PathVariable Long id) {
 return ResponseEntity.ok(userService.getUserById(id));
 }
}
4. **Create the UserIntegrationTest:**
Write code for this.
Hint: Use ‘@SpringBootTest’, ‘@AutoConfigureMockMvc’ 

Logging using SLF4J
Exercise 1: Logging Error Messages and Warning Levels
Task: Write a Java application that demonstrates logging error messages and warning levels
using SLF4J.
Step-by-Step Solution:
1. Add SLF4J and Logback dependencies to your `pom.xml` file:
<dependency>
 <groupId>org.slf4j</groupId>
 <artifactId>slf4j-api</artifactId>
 <version>1.7.30</version>
</dependency>
<dependency>
 <groupId>ch.qos.logback</groupId>
 <artifactId>logback-classic</artifactId>
 <version>1.2.3</version>
</dependency>
2. Create a Java class that uses SLF4J for logging:
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class LoggingExample {
 private static final Logger logger = LoggerFactory.getLogger(LoggingExample.class);
 public static void main(String[] args) {
 logger.error("This is an error message");
 logger.warn("This is a warning message");
 }
}
Exercise 2: Parameterized Logging
Task: Write a Java application that demonstrates parameterized logging using SLF4J.
Step-by-Step Solution:
1. Add SLF4J and Logback dependencies to your `pom.xml` file:
<dependency>
 <groupId>org.slf4j</groupId>
 <artifactId>slf4j-api</artifactId>
 <version>1.7.30</version>
</dependency>
<dependency>
 <groupId>ch.qos.logback</groupId>
 <artifactId>logback-classic</artifactId>
 <version>1.2.3</version>
</dependency>
2. Create a Java class that uses SLF4J for parameterized logging:
Write code for this.
Exercise 3: Using Different Appenders
Task: Write a Java application that demonstrates using different appenders with SLF4J.
Step-by-Step Solution:
1. Add SLF4J and Logback dependencies to your `pom.xml` file:
<dependency>
 <groupId>org.slf4j</groupId>
 <artifactId>slf4j-api</artifactId>
 <version>1.7.30</version>
</dependency>
<dependency>
 <groupId>ch.qos.logback</groupId>
 <artifactId>logback-classic</artifactId>
 <version>1.2.3</version>
</dependency>
2. Create a `logback.xml` configuration file to define different appenders:
<configuration>
 <appender name="console" class="ch.qos.logback.core.ConsoleAppender">
 <encoder>
 <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
 </encoder>
 </appender>
 <appender name="file" class="ch.qos.logback.core.FileAppender">
 <file>app.log</file>
 <encoder>
 <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
 </encoder>
 </appender>
 <root level="debug">
 <appender-ref ref="console" />
 <appender-ref ref="file" />
 </root>
</configuration>
3. Create a Java class that uses SLF4J for logging:
Write code for this. 