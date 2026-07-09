Exercise 5: Validating country code

As the POST request is a plain text, there are good possibilities to key in incorrect data. Moreover, hackers might try to pass inconsistent data which might affect the integrity of the application. Hence it becomes important that necessary checks are in place for all the fields. In this hands on we will take a simple validation criteria and will see how it can be implemented. The country code needs to be validated and ensured that it does not exceed more than 2 characters.

Refer the steps below to incorporate the same:

1. Open Country.java and include below annotations for the code property. @NotNull ensures that code is not null. @Size ensures that the width is exactly 2 characters.

```java
@NotNull
@Size(min=2, max=2, message="Country code should be 2 characters")
private String code;
```

2. In CountryController.addCountry() method add below lines after the logger. This uses the javax.validation specification to check if the bean has errors based on the annotations defined in the earlier step. All new class references in this code snippet need to be imported from javax.validation.

```java
// Create validator factory
ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
Validator validator = factory.getValidator();
// Validation is done against the annotations defined in country bean
Set<ConstraintViolation<Country>> violations = validator.validate(country);
List<String> errors = new ArrayList<String>();
// Accumulate all errors in an ArrayList of type String
for (ConstraintViolation<Country> violation : violations) {
    errors.add(violation.getMessage());
}
// Throw exception so that the user of this web service receives appropriate error message
if (violations.size() > 0) {
    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors.toString());
}
```

3. Invoke the service using curl and check the response. Refer sample response below:

```
HTTP/1.1 400
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Wed, 02 Oct 2019 10:28:56 GMT
Connection: close
{"timestamp":"2019-10-02T10:28:56.506+0000","status":400,"error":"Bad Request","message":"[Country code should be 2 characters]","path":"/countries"}
```

Question for all Learners - What needs to be done if there is another controller EmployeeController and similar validation needs to be done for Employee payload data? SME to explain the disadvantage of the above solution. This disadvantage will be overcome in the next hands on.
