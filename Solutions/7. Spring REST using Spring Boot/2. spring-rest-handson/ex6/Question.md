Exercise 6: REST - Get country exceptional scenario

In the previous hands on where we implemented getting country based on country code, what happens if the country code provided in the URL is not present.

Refer steps below to implement:

1. Create a new exception class com.cognizant.springlearn.service.exception.CountryNotFoundException
2. Include below specified annotation at the class level in CountryNotFoundException class

```java
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Country not found")
```

3. In CountryService.getCountry() method include the logic to throw CountryNotFoundException if the country code does not exist in the list.
4. In CountryController.getCountry() method include throws clause in method signature. This will respond to the caller of the web service with appropriate error message in JSON format.
5. Test the service in postman and using curl command. Refer below for executing curl command.

Steps to invoke RESTful Web Service using curl command:
1. Open Git Bash
2. Execute the below command

```
curl -i http://localhost:8090/country/az
```

Sample Request: http://localhost:8083/country/az

Sample Response:
```json
{
  "timestamp": "2019-10-02T03:27:54.521+0000",
  "status": 404,
  "error": "Not Found",
  "message": "Country not found",
  "path": "/country/az"
}
```
