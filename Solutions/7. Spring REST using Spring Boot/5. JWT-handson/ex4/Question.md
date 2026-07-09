Exercise 4: Create authentication controller and configure it in SecurityConfig

Create authentication service that returns JWT. As part of first step of JWT process, the user credentials need to be sent to an authentication service request that generates and returns the JWT. Ideally when the below curl command is executed that calls the new authentication service, the token should be responded. Note that the credentials are passed using the -u option.

Request:
```
curl -s -u user:pwd http://localhost:8090/authenticate
```

Response:
```
{"token":"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNTcwMzc5NDc0LCJleHAiOjE1NzAzODA2NzR9.t3LRvlCV-hwKfoqZYlaVQqEUiBloWcWn0ft3tgv0dL0"}
```

This can be incorporated as three major steps (implemented as separate hands on exercises):
1. Create authentication controller and configure it in SecurityConfig
2. Read Authorization header and decode the username and password
3. Generate token based on the user retrieved in the previous step

This exercise covers step 1 — AuthenticationController.java:

1. Create new rest controller named AuthenticationController in controller package
2. Include method authenticate with "/authenticate" as the URL with @GetMapping.
3. To read the Authorization value from HTTP Header, include a parameter for authenticate method as specified below. Spring takes care of reading the Authorization value from HTTP Header and passing it as parameter.

```java
@RequestHeader("Authorization") String authHeader
```

4. The return type of this method should be Map<String, String>
5. Include start and end logger in this method
6. Include a debug log for displaying the authHeader parameter
7. Create a new HashMap<String, String> and assign it to a map.
8. Put a new item into the map with key as "token" and value as empty string.

SecurityConfig.java:

1. In the second configure method, include authenticate URL just after the countries URL defined earlier. Refer code below:

```java
.antMatchers("/countries").hasRole("USER")
.antMatchers("/authenticate").hasAnyRole("USER", "ADMIN")
```

The above configuration sets that users of both roles can access /authenticate URL.

Testing curl command:
```
curl -s -u user:pwd http://localhost:8090/authenticate
```

Expected Response:
```
{"token":""}
```

Log verification: Check if Authorization header value is displayed with "Basic" prefix and Base64 encoding of "user:pwd"
