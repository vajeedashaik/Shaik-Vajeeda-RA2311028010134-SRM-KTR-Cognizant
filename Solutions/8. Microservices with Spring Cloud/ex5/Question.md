# Exercise: API Gateway with Logging Filter

**Source:** "2. Microservices with API gateway.pdf" / "3. Microservices composite handson.docx" (Questions/Microservices) — section "Create a Spring Cloud API Gateway and call one microservice thru the API gateway"
(Supplementary generic requirements also appear in "1. Microservices using Spring Boot 3 exercises.pdf", Exercise 3 "Implement an API Gateway".)

## Task

Create a Spring Cloud API Gateway and call one microservice (`greet-service`, see `ex4`) through
the API gateway. Configure a global filter to log each request targeting the microservice.

## Steps

1. Create another microservice, `api-gateway`, using Spring Initializr with the latest Spring
   Boot version and the `Gateway` (Spring Cloud Routing), `Eureka Discovery Client`, and
   `Spring Boot Actuator` / DevTools dependencies.
2. Configure `application.properties`:
   ```properties
   server.port=9090
   spring.application.name=api-gateway
   spring.cloud.gateway.discovery.locator.enabled=true
   spring.cloud.gateway.discovery.locator.lower-case-service-id=true
   ```
3. Run `api-gateway` and confirm it registers with `eureka-discovery-server`
   (`http://localhost:8761`) alongside `greet-service`.
4. Access `greet-service` through the gateway using the discovered, lower-cased service id, e.g.
   `http://localhost:9090/greet-service/greet`, and confirm it returns "Hello World!!".
5. Implement a global filter that logs all incoming requests:
   ```java
   @Component
   public class LogFilter implements GlobalFilter {
       Logger logger = LoggerFactory.getLogger(LogFilter.class);

       @Override
       public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
           logger.info("====>Request URL {}", exchange.getRequest().getURI());
           return chain.filter(exchange);
       }
   }
   ```
6. Re-access the gateway URL and confirm the request is logged to the console.

## Supplementary / extra-credit requirements

From "1. Microservices using Spring Boot 3 exercises.pdf" (Exercise 3, generic Customer/Billing
gateway scenario) — as a stretch goal, extend the gateway to also demonstrate:
- Rate limiting
- Response caching
- Path rewriting

using Spring Cloud Gateway's built-in `RequestRateLimiter`, caching, and `RewritePath` filters.
