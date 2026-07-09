Exercise 5: Read Authorization header and decode the username and password

Steps to read and decode header:

1. Create a new private method in AuthenticationController with below method signature

```java
private String getUser(String authHeader)
```

2. Get the Base64 encoded text after "Basic "
3. Decode it using the library available in Java 8 API. Refer code below.

```java
Base64.getDecoder().decode(encodedCredentials)
```

4. The above call returns a byte array, which can be passed as parameter to string constructor to convert to string.
5. Get the text until colon on the string created in previous step to get the user
6. Return the user obtained in previous step
7. Include appropriate debug logs within this method
8. Invoke the getUser() method from authenticate method
9. Execute the curl command used in the previous step and check the logs if the user information is obtained successfully.
