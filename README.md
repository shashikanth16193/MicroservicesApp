MicroservicesApp

A Spring Boot Microservices-based application that demonstrates core microservices patterns like Service Discovery, API Gateway, Centralized Configuration, Authentication & Authorization with JWT


Implemented Features :-

auth-service :-

User login with email & password

JWT token generation with role-based access control (RBAC)

Role support: STUDENT, TEACHER, ADMIN


course-service :-

Teachers can create and manage courses

Students can view available courses

JWT-based identity extraction (no ID from request body)


review-service :-

Students can post reviews for courses

Teachers/Admins can moderate reviews


api-gateway :-

Routes requests to appropriate services

Validates JWT tokens before forwarding

Implements Circuit Breaker & Rate Limiter using Resilience4j


eureka-server (Service Discovery) :-

Each service registers itself

Gateway & other services discover dynamically


config-server (Centralized Configuration) :-

Externalized configuration stored in Git repository

All services fetch config at startup


IN PROGRESS OR PARTIAL IMPLMENTATION   

Inter-Service Communication

Uses Feign Client and RestTemplate

Circuit Breaker and Rate Limiter on api-gateway


Tech Stack

Java 8

Spring Boot (Web, JPA, Security, Actuator)

Spring Cloud (Eureka, Config Server, Gateway)

H2 Database (in-memory)

Docker & Docker Compose


📂 Project Structure

    LocalSetup/
    
    ├── MicroservicesApp/
   
         │── auth-service/          # Authentication & JWT
       
         │── course-service/        # Course management
       
         │── review-service/        # Review management
       
         │── api-gateway/           # API Gateway 
       
         │── eureka-server/         # Service Discovery
       
         │── config-server/         # Centralized config
       
         └── docker-compose.yml     # Runs all services together
       
     └── AppConfigs/           # contains config files
   
   

Getting Started
1. Create LocalSetup folder

  cd LocalSetup

3. Clone the MicroservicesApp repo

git clone https://github.com/shashikanth16193/MicroservicesApp.git

3. Clone the AppConfigs repo

git clone https://github.com/shashikanth16193/AppConfis.git

4. Start Services with Docker

cd LocalSetup/MicroservicesApp

docker-compose up

5.Postman collection for testing

Future Enhancements

Exception and Error Handling

Implementing Resilience4j patterns across down stream micro services
Centralized Logging with ELK (ElasticSearch, Logstash, Kibana)
Distributed Tracing with Zipkin
