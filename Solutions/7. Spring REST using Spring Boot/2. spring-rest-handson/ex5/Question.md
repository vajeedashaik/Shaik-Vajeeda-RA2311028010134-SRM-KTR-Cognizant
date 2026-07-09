Exercise 5: REST - Get country based on country code

Write a REST service that returns a specific country based on country code. The country code should be case insensitive.

- Controller: com.cognizant.spring-learn.controller.CountryController
- Method Annotation: @GetMapping("/countries/{code}")
- Method Name: getCountry(String code)
- Method Implementation: Invoke countryService.getCountry(code)
- Service Method: com.cognizant.spring-learn.service.CountryService.getCountry(String code)

Service Method Implementation:
1. Get the country code using @PathVariable
2. Get country list from country.xml
3. Iterate through the country list
4. Make a case insensitive matching of country code and return the country.
   - Lambda expression can also be used instead of iterating the country list

Sample Request: http://localhost:8083/country/in

Sample Response:
```json
{
  "code": "IN",
  "name": "India"
}
```
