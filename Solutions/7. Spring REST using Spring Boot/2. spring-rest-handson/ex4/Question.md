Exercise 4: REST - Get all countries

Write a REST service that returns all the countries.

- Controller: com.cognizant.spring-learn.controller.CountryController
- Method Annotation: @GetMapping("/countries")
- Method Name: getAllCountries()
- Method Implementation: Load country list from country.xml and return
- Sample Request: http://localhost:8083/countries
- Sample Response:

```json
[
  { "code": "IN", "name": "India"},
  { "code": "US", "name": "United States"},
  { "code": "JP", "name": "Japan"},
  { "code": "DE", "name": "Germany"}
]
```
