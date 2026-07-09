Exercise 6: Generate token based on the user

Steps to generate token:

1. Include JWT library by including the following maven dependency.

```xml
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt</artifactId>
    <version>0.9.0</version>
</dependency>
```

2. After inclusion in pom.xml, run the maven package command line and update the project in Eclipse. View the dependency tree and check if the library is added.
3. Create a new method in AuthenticationController with below method signature:

```java
private String generateJwt(String user)
```

4. Generate the token based on the code specified below.

```java
JwtBuilder builder = Jwts.builder();
builder.setSubject(user);
// Set the token issue time as current time
builder.setIssuedAt(new Date());
// Set the token expiry as 20 minutes from now
builder.setExpiration(new Date((new Date()).getTime() + 1200000));
builder.signWith(SignatureAlgorithm.HS256, "secretkey");
String token = builder.compact();
return token;
```

Import reference for the above code:
```java
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
```

5. Invoke this method from authenticate() method passing the user obtained from getUser() method.
6. Add the token into the map using put method.
7. Include appropriate logs
8. Execute the curl command for authenticate and check if the generated token is returned.
