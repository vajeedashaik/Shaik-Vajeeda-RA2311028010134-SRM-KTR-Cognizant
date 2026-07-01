Exercise 1: Parameterized Tests 
Scenario: 
You want to test a method that checks if a number is even. Instead of writing multiple test 
cases, you will use parameterized tests to run the same test with different inputs. 
Steps: 
1. Create a new Java class `EvenChecker` with a method `isEven(int number)`. 
2. Write a parameterized test class `EvenCheckerTest` that tests the `isEven` method with 
different inputs. 
3. Use JUnit's `@ParameterizedTest` and `@ValueSource` annotations. 