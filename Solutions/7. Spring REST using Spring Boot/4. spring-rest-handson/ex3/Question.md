Exercise 3: Create RESTful Web Service to handle POST request of Country

A new RESTful Web Service method to handle POST request of Country. Follow steps below to incorporate the same:

1. Create new method in CountryController based on the following details:
   - Annotation - @PostMapping()
   - Method Signature - public void addCountry()
   - Within this method include "Start" logger.
2. Start the web application
3. Open Git Bash
4. Execute the following curl command, to invoke the web service:
   - -i to display the headers
   - -X to define the HTTP method type
   - -s silent mode, so that performance details are not displayed

```
curl -i -X POST -s http://localhost:8090/countries
```

5. Check if "Start" is displayed in the console output

Following is the expected output:
```
HTTP/1.1 200
Content-Length: 0
Date: Tue, 01 Oct 2019 06:41:49 GMT
```

The invocation of web service can also be done using Postman. Check the logger if "Start" is logged.
