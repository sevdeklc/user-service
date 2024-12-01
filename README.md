# User Service

##### Goal

Writing a microservice for performing user operations

## Table of Contents
1. [Tech Stack](#tech-stack)
2. [Requirements](#requirements)
3. [Database Configuration](#database-configuration)
4. [Running the Application](#running-the-application)
5. [Checkstyle](#checkstyle)

## Tech Stack

- [Spring Boot 3.3.6](https://spring.io/blog/2024/11/21/spring-boot-3-3-6-available-now)
- [JDK 21](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven](https://maven.apache.org)
- OpenAPI Documentation
- MySQL
- Docker
- Junit 5
- JPA
- Restful API
- [Checkstyle 9.2](https://checkstyle.org/)

## Requirements

For building and running the application belows are required;

- Spring Boot 3.3.6
- JDK 21
- Maven
- MySQL

## Database Configuration

To run the User Service, you need to set up a database schema named `user-service` in your MySQL database.

Open the application.properties file and provide the appropriate username and password to establish a connection with the database.

```
spring.datasource.username=<your_username>
spring.datasource.password=<your_password>
```

## Running the application

The Application is running on port ```8080``` and can be run with the ```Application``` class under the com.user.service

```shell
mvn spring-boot:run
```

## Checkstyle

In our project, the config file is checkstyle.xml.
If we run the ```mvn clean install``` command, it will scan the files for violations and the build will fail if any violations are found.
We can also run only the check goal of the plugin using ```mvn checkstyle:check```, without configuring the execution goal.

Once the build finishes, the report is available in the target folder under the name checkstyle-result.xml
