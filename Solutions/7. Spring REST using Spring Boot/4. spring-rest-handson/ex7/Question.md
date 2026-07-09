Exercise 7: Response with bad request in global exception handler

1. Include the below code in the handleMethodArgumentNotValid() method:

```java
// Map that contains the error details
Map<String, Object> body = new LinkedHashMap<>();
body.put("timestamp", new Date());
body.put("status", status.value());
// Get all validation errors
List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
        .collect(Collectors.toList());
// Add errors to the response map
body.put("errors", errors);
LOGGER.info("End");
return new ResponseEntity<>(body, headers, status);
```

2. Execute the updated web application and execute the curl command with single character for country code
3. See expected response below.

```
HTTP/1.1 400
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Thu, 03 Oct 2019 04:10:17 GMT
Connection: close
{"timestamp":"2019-10-03T04:10:17.277+0000","status":400,"errors":["Country code should be 2 characters"]}
```
