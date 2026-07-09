Exercise 2: RESTful Web Service resource naming guidelines

Find below the guidelines to define a RESTful Web Service URL:

- Each resource should have a unique and specific URL
- To get all resources provide the resource name in plural
- To get a specific resource provide resource name in plural followed with slash and parameter
- To create a resource the URL should be the resource name in plural and the data to create the resource should be defined in the payload
- To update a resource the URL should be the resource name in plural with data in payload
- To delete a resource the URL should be the resource name in plural followed by slash and the specific resource to delete
- Resource name with multiple words should be separated by hyphen and not with underscore. For example, if the resource is menu item implement the URL as "menu-item".

Refer table below with example for resource as country.

| Method Type | URL | Description | Annotation |
|-------------|-----|-------------|------------|
| GET | http://sample.api.com/app-name/countries | Get all countries | @GetMapping |
| GET | http://sample.api.com/app-name/countries/{code} | Get a specific country | @GetMapping("/{id}") |
| POST | http://sample.api.com/app-name/countries | Create country based on data in post | @PostMapping |
| PUT | http://sample.api.com/app-name/countries | Update country based on data in post | @PutMapping |
| DELETE | http://sample.api.com/app-name/countries/{code} | Delete a specific country | @DeleteMapping("/{id}") |

For a particular resource, the URL should be the same for all the methods. Hence in CountryController, the URL can be defined at the class level:

```java
@RequestMapping("/countries")
```

Find below the method specific annotation definitions:
- Get All: `@GetMapping`
- Get specific resource: `@GetMapping("/{id}")`
- Create resource: `@PostMapping` (NOTE: Payload data should be sent in the body of the request)
- Update resource: `@PutMapping` (NOTE: Payload data should be sent in the body of the request)
- Delete resource: `@DeleteMapping("/{id}")`

Going forward ensure that this convention is followed when defining a new service. Modify CountryController to adhere to the above mentioned standards.
