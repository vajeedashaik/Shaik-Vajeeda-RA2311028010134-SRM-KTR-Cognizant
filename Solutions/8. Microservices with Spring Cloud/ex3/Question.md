# Exercise: Loan Microservice

**Source:** "2. Microservices with API gateway.pdf" / "3. Microservices composite handson.docx" (Questions/Microservices) — section "Creating Microservices for account and loan"

## Task

Following on from the account microservice (`ex2`), create a second, independent microservice
for handling loan accounts. Like the account service, this is a simple Spring RESTful Web
service Maven project with its own `pom.xml` and no backend connectivity — it just returns
dummy data.

## Steps

1. Follow the same project-setup steps used for the Account Microservice and implement a
   controller method to get loan account details:
   - Method: `GET`
   - Endpoint: `/loans/{number}`
   - Sample (dummy) response:
     ```json
     { "number": "H00987987972342", "type": "car", "loan": 400000, "emi": 3258, "tenure": 18 }
     ```
2. Launch this application while the account service is already running. The launch will fail
   with a "bind address already in use" error, because both services default to port 8080 and
   the account service already holds that port.
3. Add `server.port=8081` to `application.properties` and relaunch the application.
4. Test the service on port 8081.

At this point there are two independent microservices running on different ports.

## Follow-on integration (see also ex1)

- Add the Eureka Discovery Client dependency and `@EnableDiscoveryClient` to the application class.
- Set `spring.application.name=loan-service` in `application.properties`.
- Start `eureka-discovery-server` first, then this service, and confirm `loan-service` appears
  in the Eureka dashboard at `http://localhost:8761`.
