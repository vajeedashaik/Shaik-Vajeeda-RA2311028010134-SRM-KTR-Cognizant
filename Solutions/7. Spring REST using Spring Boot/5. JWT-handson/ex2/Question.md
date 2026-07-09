Exercise 2: Creating users and roles in Spring Security

The earlier hands on demonstrated securing all URLs of the application with a common password. But it is not user and role specific. Let us create two new in memory users with names 'admin' and 'user'. The password for both the users will be 'pwd'. Let us define the rule that getting all countries can be accessed only by 'user'.

Refer steps below to incorporate the above aspects:

1. Include the below methods in the SecurityConfig class

```java
@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()
        .withUser("admin").password(passwordEncoder().encode("pwd")).roles("ADMIN")
        .and()
        .withUser("user").password(passwordEncoder().encode("pwd")).roles("USER");
}

@Bean
public PasswordEncoder passwordEncoder() {
    LOGGER.info("Start");
    return new BCryptPasswordEncoder();
}

@Override
protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.csrf().disable().httpBasic().and()
        .authorizeRequests().antMatchers("/countries").hasRole("USER");
}
```

- The first configure() method defines two users admin and user with password as pwd. It also includes the specification of respective roles.
- IMPORTANT NOTE: For learning purpose we are hard coding user details. When working on Spring Data JPA module, the credentials will be validated from the database.
- The password encoder is required to encrypt the password.
- The second configure() method defines that /countries service is accessible only to users of role "USER"

2. For testing the service with right credentials:
```
curl -s -u user:pwd http://localhost:8090/countries
```

3. For testing the service with incorrect credentials:
```
curl -s -u user:pwd1 http://localhost:8090/countries
```
```
{"timestamp":"2019-10-05T10:19:08.237+0000","status":401,"error":"Unauthorized","message":"Unauthorized","path":"/countries"}
```

4. For testing the service with correct credentials but a different role:
```
curl -s -u admin:pwd http://localhost:8090/countries
```
```
{"timestamp":"2019-10-05T10:22:38.015+0000","status":403,"error":"Forbidden","message":"Forbidden","path":"/countries"}
```

Limitations of this security approach:
- RESTful Web Service is a stateless protocol, hence each request needs to have the user id and password credentials attached.
- The credentials passed on the HTTP request are not secure. Refer steps below to understand this better:
  1. Execute the below command to display the request and response headers:
     ```
     curl -s -v -u admin:pwd http://localhost:8090/countries
     ```
  2. In the result display, in the request section, refer the Authorization header: `Authorization: Basic YWRtaW46cHdk`
  3. If "admin:pwd" is encoded with Base64 it results in "YWRtaW46cHdk"
  4. Search using google and find an online website that can decode Base64 (Example: https://www.base64decode.net/)
  5. Try decoding YWRtaW46cHdk using the website and one can obtain "admin:pwd"

These limitations can be overcome by incorporating security using JWT. Subsequent hands on will address this issue.
