# Exercise: Greet Microservice (Gateway Target)

**Source:** "2. Microservices with API gateway.pdf" / "3. Microservices composite handson.docx" (Questions/Microservices) — section "Create a Spring Cloud API Gateway and call one microservice thru the API gateway"

## Task

Create a Spring Cloud API Gateway and call one microservice through the API gateway. Configure
a global filter to log each request targeting the microservice using Spring Cloud API Gateway.
This exercise covers step 1 of that walkthrough: building the simple target microservice,
`greet-service`, that the gateway (see `ex5`) will route to.

## Steps

1. Create a simple microservice named `greet-service` using Spring Initializr that returns
   "Hello World".
2. Select the latest Spring Boot version with the `Spring Web` dependency (and DevTools).
3. Configure the application name in `application.properties`:
   ```properties
   spring.application.name=greet-service
   ```
4. Create a controller:
   ```java
   @RestController
   public class GreetController {
       @GetMapping("/greet")
       public String sayHello() {
           return "Hello World!!";
       }
   }
   ```
5. Run the microservice and verify `http://localhost:8080/greet` returns "Hello World!!".

## Follow-on integration (see also ex1, ex5)

- Add the Eureka Discovery Client dependency (matching the spring-cloud version used by
  `eureka-discovery-server`) so this service can register itself and be routed to by
  `api-gateway`.
- Restart the service and confirm `GREET-SERVICE` appears in the Eureka dashboard at
  `http://localhost:8761`.
