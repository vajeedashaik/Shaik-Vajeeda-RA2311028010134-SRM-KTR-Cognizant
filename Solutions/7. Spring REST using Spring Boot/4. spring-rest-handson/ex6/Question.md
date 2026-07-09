Exercise 6: Include global exception handler for validation errors

Following steps create a global validation error handler. This will validate all errors that may happen in any controller.

Create global exception handler:

1. Create class com.cognizant.springlearn.GlobalExceptionHandler that extends ResponseEntityExceptionHandler with annotation @ControllerAdvice
2. Include method handler for handling the validation error and include a start logger within the method implementation.

```java
@Override
protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
        HttpHeaders headers, HttpStatus status, WebRequest request) {
    LOGGER.("Start");
}
```

Refer imports below:
```java
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
```

3. Include @Valid annotation in the addCountry() method. This intimates spring framework to validate the country bean based on the validation annotations added in the Country class. Refer code below:

```java
public Country addCountry(@RequestBody @Valid Country country)
```

4. Remove all the validation code included in the previous hands on.
5. Run the application and invoke the curl request with single character for country code.

```
curl -i -H 'Content-Type: application/json' -X POST -s -d '{"code":"I","name":"India"}' http://localhost:8090/countries
```

6. Check the logs and see if the start logger is present. Also notice that the logs of CountryController is not present, which means that the global exception handler method is called if there are validation errors and the controller method is not invoked.
