
T&S Allowance Application System – How to Run
Prerequisites

Before starting, make sure the following are installed:

Java JDK 17+ (for Spring Boot backend)

Maven 3+ (for building backend)

Node.js 18+ and npm (for React frontend)

PostgreSQL 12+ database

1. Setup PostgreSQL Database

Open PostgreSQL and create a database:

CREATE DATABASE tsallowance;


Create a user (replace tsuser and password123 with your credentials):

CREATE USER tsuser WITH PASSWORD 'password123';
GRANT ALL PRIVILEGES ON DATABASE tsallowance TO tsuser;


Note the following connection details:

Database Name: tsallowance

Username: tsuser

Password: password123

Host: localhost

Port: 5432

2. Configure Spring Boot Backend

Open the Spring Boot project in your IDE (VSCode, IntelliJ, etc.).

Edit src/main/resources/application.properties to match your PostgreSQL credentials:

spring.datasource.url=jdbc:postgresql://localhost:5432/tsallowance
spring.datasource.username=tsuser
spring.datasource.password=password123
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
server.port=9076


server.port=9076 ensures the backend runs on port 9076.

Make sure CORS is enabled for the frontend (React) running on port 3000:

@Configuration
public class WebConfig {
@Bean
public WebMvcConfigurer corsConfigurer() {
return new WebMvcConfigurer() {
@Override
public void addCorsMappings(CorsRegistry registry) {
registry.addMapping("/**")
.allowedOrigins("http://localhost:3000")
.allowedMethods("GET","POST","PUT","DELETE");
}
};
}
}

3. Run the Backend

Open a terminal in the backend project folder.

Build and run the application:

mvn clean install
mvn spring-boot:run


Backend should start on http://localhost:9076.

Verify by opening http://localhost:9076/api/health (if a health endpoint exists) or test with Postman
