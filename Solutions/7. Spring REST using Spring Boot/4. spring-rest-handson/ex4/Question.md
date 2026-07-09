Exercise 4: Read country data as a bean in RESTful Web Service

The country data should be included in the request payload, which should be read by the controller method. Follow steps below to incorporate the same:

1. Include country as parameter to addCountry() method with @RequestBody annotation and country as parameter. Refer method signature below.

```java
public Country addCountry(@RequestBody Country country)
```

2. Include log to display country details
3. Return the country. This is to check if country details are populated correctly
4. Invoke the service using the following curl command. This can also be tried for execution from Postman.
   - -H denotes inclusion of header. This denotes that we are sending content type in the request header and it mentions that the request payload is of type JSON
   - -d denotes the data payload sent in the request. This represents the country to be added

```
curl -i -H 'Content-Type: application/json' -X POST -s -d '{"code":"IN","name":"India"}' http://localhost:8090/countries
```

Refer the expected HTTP response below:
```
HTTP/1.1 200
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Tue, 01 Oct 2019 17:23:47 GMT
{"code":"IN","name":"India"}
```

Try running the request with minor change and let us see the response. Sample response below. The attribute name is intentionally provided with a spelling mistake.

```
curl -i -H 'Content-Type: application/json' -X POST -s -d '{"code":"IN","nae":"India"}' http://localhost:8090/countries
```

Refer the expected HTTP response below:
```
HTTP/1.1 200
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Tue, 01 Oct 2019 17:23:47 GMT
{"code":"IN","name":null}
```

SME to provide explanation about the following aspects:
- Explain how spring framework takes care of converting the request payload into country bean
  - Spring parses the JSON request payload data using Jackson parser
  - For each attribute in JSON, respective method name is constructed by applying initcaps and get prefix. For example, the name attribute is changed with initcaps as Name, then get is prefixed to it which results in getName, based on this the respective method is invoked using Reflection API.
  - Spring creates country object and invokes the respective setter method based on JSON data.
  - Then it invokes the controller method passing the country object created
- Provide explanation regarding bean naming conventions
