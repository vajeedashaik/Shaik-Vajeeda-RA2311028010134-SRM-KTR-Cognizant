Exercise 8: MockMVC - Test get country service for exceptional scenario

Include MockMVC test that checks if correct response is received when there is an error.

Refer steps below to implement:

1. Include a new test method testGetCountryException() in SpringLearnApplicationTests.java
2. Validate the error response using status(). Refer code below.

```java
actions.andExpect(status().isBadRequest());
actions.andExpect(status().reason("Country Not found"));
```
