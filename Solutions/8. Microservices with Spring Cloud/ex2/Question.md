# Exercise: Account Microservice

**Source:** "2. Microservices with API gateway.pdf" / "3. Microservices composite handson.docx" (Questions/Microservices) — section "Creating Microservices for account and loan"

## Task

In this hands-on exercise we create two independent microservices for a bank: one for handling
accounts and one for handling loans (see `ex3` for the loan microservice). Each microservice is
its own independent Spring RESTful Web service Maven project with its own `pom.xml` — instead of
combining account and loan handling into a single application, the functionality is split into
two separate applications. These are simple services with no backend/database connectivity;
they simply return dummy data.

## Steps — Account Microservice

1. Go to https://start.spring.io/ and generate a project with:
   - Group: `com.cognizant`
   - Artifact: `account`
   - Dependencies: Spring Boot DevTools, Spring Web
2. Build the project with `mvn clean package`.
3. Implement a controller method to get account details by account number:
   - Method: `GET`
   - Endpoint: `/accounts/{number}`
   - Sample (dummy) response:
     ```json
     { "number": "00987987973432", "type": "savings", "balance": 234343 }
     ```
4. Run the application and verify the endpoint in a browser.

## Follow-on integration (see also ex1)

- Add the Eureka Discovery Client dependency and `@EnableDiscoveryClient` to the application class.
- Set `spring.application.name=account-service` in `application.properties` so the service is
  identified correctly in the Eureka registry.
- Start `eureka-discovery-server` first, then start this service, and confirm `account-service`
  appears in the Eureka dashboard at `http://localhost:8761`.
