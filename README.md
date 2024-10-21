# Technical test Joan Florit

This is an example of a Java application with Spring using a hexagonal architecture and a database H2.

The database model is created using the _PriceEntity_, and _data.sql_ for inserting the prices.

To run the application, we have the following class: _PriceApplication_

Database access:

> [Database H2](http://localhost:8080/h2/)

Access to Swagger UI:

> [Swagger UI](http://localhost:8080/swagger-ui/index.html)

Access to API docs:

> [Api Docs](http://localhost:8080/v1/api-docs)

Service endpoint:

> [GET Prices by params](http://localhost:8080/v1/prices)

## Run Spring Boot application

```
mvn spring-boot:run
```

## Run Unit Tests

```
mvn test
```

**JUnit Tests:** PriceServiceTest and PricePersistenceAdapterTest.

**RestAssured Test:** PriceRestAdapterTest

**Cucumber Test:** price.feature which runs the PriceSteps.

Example of a cURL request (for Postman):

```
curl --request GET \
--url 'http://localhost:8080/v1/prices?date=2020-06-16T21:00:00&productId=35455&brandId=1'
```

## Build the JAR file for Docker

```
mvn clean package
```

## Build the Docker Image

```
docker build -t my-spring-app-0.0.1-SNAPSHOT.jar
```

## Run the Docker Container

```
docker run -p 8080:8080 my-spring-app-0.0.1-SNAPSHOT.jar
```

## Explanation about hexagonal architecture API

The main idea behind this architecture is to isolate domain logic from external components when designing software applications.

We have the three layers: application, domain and infrastructure:

<img src="https://miro.medium.com/v2/resize:fit:420/1*N7tlxqOLXiPLy_yrb0q7-Q.png"/>

Layers of hexagonal architecture:

- Application: will contain the ports, which are interfaces that allow inbound or outbound flow.
- Infrastructure: represents the outer part of the hexagonal architecture through adapters.
- Domain: the center of the system. It handles the business logic and represents the application core.
