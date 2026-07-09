Exercise 8: Implement REST service for updating an employee

Based on the learning done with REST service for country, implement a service to update employee details. Follow steps below to incorporate the same:

1. Include below validations in Employee, Department and Skill beans

Employee:
- id - should not be null, should be a number
- name - should not be null, should not be blank, minimum 1 character and maximum 30 characters
- salary - should not be null, should be zero or above
- permanent - should not be null
- dateOfBirth - should match the date pattern. Use below annotation

```java
@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
```

Department:
- id - should not be null, should be a number
- name - should not be null, should not be blank, minimum 1 character and maximum 30 characters

Skill:
- id - should not be null, should be a number
- name - should not be null, should not be blank, minimum 1 character and maximum 30 characters

2. Implement the Employee service with below aspects incorporated:
   - Define EmployeeNotFoundException with HttpStatus annotation
   - Include updateEmployee() method in EmployeeDao that modifies employee list. If the employee is not found throw EmployeeNotFoundException.
   - Include updateEmployee() method in EmployeeService that invokes the dao update employee method
   - Include updateEmployee() method in EmployeeController with below signature with @PutMapping annotation. Refer method signature below:

```java
public void updateEmployee(@RequestBody @Valid Employee employee) throws EmployeeNotFoundException
```

   - Follow necessary URL guidelines for the above method signature.
3. If string value is included in a numeric field (for example: id), the failure happens even before validation, include a new method in global exception handler which handles this scenario. Refer code below:

```java
protected ResponseEntity<Object> handleHttpMessageNotReadable(
        HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status,
        WebRequest request) {
    Map<String, Object> body = new LinkedHashMap<>();
    body.put("timestamp", new Date());
    body.put("status", status.value());
    body.put("error", "Bad Request");
    List<String> errors = new ArrayList<String>();
    if (ex.getCause() instanceof InvalidFormatException) {
        final Throwable cause = ex.getCause() == null ? ex : ex.getCause();
        for (InvalidFormatException.Reference reference : ((InvalidFormatException) cause).getPath()) {
            body.put("message", "Incorrect format for field '" + reference.getFieldName() + "'");
        }
    }
    return new ResponseEntity<>(body, headers, status);
}
```

4. Test the service using Postman passing the employee data as JSON, which should include department and skills.
5. Using Postman invoke get all employees service to verify if the update is reflected
6. Include MockMvc test for the exceptional scenario
