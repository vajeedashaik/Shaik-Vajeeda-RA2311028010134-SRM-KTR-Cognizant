Exercise 7: Authorize based on JWT

Let us recollect the JWT Process:
1. Client sends username and password to server
2. Server validates credentials, creates token (JWT) and responds back
3. Client attaches the token in subsequent requests to server
4. Server validates the token (JWT) on each client request

Points 1-3 are already implemented. Now all application-related requests coming in should send the token received and the server needs to incorporate this validation. So far, whatever we have implemented is service specific with respective controller methods, but now the requirement is to validate all the other services provided by this application for JWT, hence we cannot use a controller here. The ideal solution would be to use a filter as it can intercept all the requests received by this application.

Follow steps below to get this incorporated:

1. Create a new class JwtAuthorizationFilter in package com.cognizant.springlearn.security
2. This new class has to extend from BasicAuthenticationFilter. This parent class is available in spring security library.
3. Include the below constructor that sets the authentication manager

```java
public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
    super(authenticationManager);
    LOGGER.info("Start");
    LOGGER.debug("{}: ", authenticationManager);
}
```

4. Override the below method to check if Authorization header contains Bearer and initiates the validation. If the validation is successful, it sets the status in spring security as authenticated.

```java
@Override
protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res,
        FilterChain chain) throws IOException, ServletException {
    LOGGER.info("Start");
    String header = req.getHeader("Authorization");
    LOGGER.debug(header);
    if (header == null || !header.startsWith("Bearer ")) {
        chain.doFilter(req, res);
        return;
    }
    UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
    SecurityContextHolder.getContext().setAuthentication(authentication);
    chain.doFilter(req, res);
    LOGGER.info("End");
}
```

5. The getAuthentication() method invoked in the above code has to be implemented within this same class as a private method. Refer code below.

```java
private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
    String token = request.getHeader("Authorization");
    if (token != null) {
        // parse the token.
        Jws<Claims> jws;
        try {
            jws = Jwts.parser()
                    .setSigningKey("secretkey")
                    .parseClaimsJws(token.replace("Bearer ", ""));
            String user = jws.getBody().getSubject();
            LOGGER.debug(user);
            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
            }
        } catch (JwtException ex) {
            return null;
        }
        return null;
    }
    return null;
}
```

6. Necessary imports for the above code:
```java
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
```

7. Now the final step is to configure the security to use the above specified filter. Modify code of 2nd configure method in SecurityConfig class.

```java
@Override
protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.csrf().disable().httpBasic().and()
        .authorizeRequests()
        //.antMatchers("/countries").hasRole("USER")
        .antMatchers("/authenticate").hasAnyRole("USER", "ADMIN")
        .anyRequest().authenticated()
        .and()
        .addFilter(new JwtAuthorizationFilter(authenticationManager()));
}
```

- First line retains the HTTP Basic authentication
- The last three lines include the new filter to validate JWT

Test: Execute below command to create a fresh token. Copy the token generated to be used for the next command.
```
curl -s -u user:pwd http://localhost:8090/authenticate
```

Execute below command to invoke any service of the application with JWT. Notice how authorization header is added with bearer and the token in request.
```
curl -s -H "Authorization: Bearer REPLACE_TOKEN_HERE" http://localhost:8090/countries
```

Execute the above command with the token slightly modified and check if Unauthorized response is received.
