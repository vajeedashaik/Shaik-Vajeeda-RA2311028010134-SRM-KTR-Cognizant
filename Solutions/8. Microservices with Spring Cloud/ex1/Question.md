# Exercise: Create Eureka Discovery Server and Register Microservices

**Source:** "2. Microservices with API gateway.pdf" / "3. Microservices composite handson.docx" (Questions/Microservices) — section "Create Eureka Discovery Server and register microservices"

## Task

Eureka Discovery Server holds a registry of all the services that are available for immediate
consumption. Anybody who wants to consume a RESTful Web Service can come to the discovery
server and find out what is available and ready for consumption. Eureka Discovery Server is
part of the Spring Cloud module.

## Steps

1. Using https://start.spring.io generate a project with the following configuration:
   - Group: `com.cognizant`
   - Artifact: `eureka-discovery-server`
   - Module: Spring Cloud Discovery > Eureka Server
2. Download the project, build it with Maven from the command line, and import it into your IDE.
3. Annotate the main application class with `@EnableEurekaServer`.
4. Add the following configuration to `application.properties`:
   ```properties
   server.port=8761
   eureka.client.register-with-eureka=false
   eureka.client.fetch-registry=false
   logging.level.com.netflix.eureka=OFF
   logging.level.com.netflix.discovery=OFF
   ```
   - This runs the discovery service on port 8761.
   - These properties prevent the discovery server itself from registering as a client; instead
     it acts purely as the registry that other services register with.
5. Launch the application and browse to `http://localhost:8761` to view the Eureka dashboard.
6. Check the "Instances currently registered with Eureka" section — it should initially be empty.
7. Register other microservices (e.g. `account-service`, `loan-service`, `greet-service`) with this
   discovery server by adding the Eureka Discovery Client dependency to each and setting
   `spring.application.name` in each service's `application.properties`. After starting the
   discovery server first, then each client service, refresh the dashboard to confirm each
   service appears in the registry.
