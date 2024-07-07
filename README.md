# Restaurant Reservation System
This back-end API application for
a **Restaurant Reservation System** is built using _Spring_ and especially _Spring Boot_.

It is a **RESTful Web Service** with provided endpoints (resources) that are
consumed from [restaurant-frontend](https://github.com/SakisHous/restaurant-frontend), the front-end of this application.

## Services and business logic

In this application, users can be registered and logged in their accounts. In addition, they can search for
a restaurant in their city, make a reservation and a review for this restaurant.
They can also see older reservations and plan the future ones for their company.
Restaurant's owners can post restaurant's menu and open hours. They come
in contact with customers for additional services.

## Running the application

In order to run the application first git clone the repository in a local
repository in your PC. For database, you have to install MySQL Server 8 and configure
the application local in your computer. 

Connecting the application with the database you have to declare some variables
in the *application.properties* file.

```
spring.datasource.url=jdbc:mysql://localhost:3306/${JAVA_REST_DB}?serverTimezone=UTC
spring.datasource.username=${JAVA_REST_USER}
spring.datasource.password=${JAVA_REST_PASSWORD}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=create
spring.web.locale=el_GR
spring.web.locale-resolver=fixed

jwt.secret.key=${JAVA_REST_SECRET_KEY}
```

In the above script they properties are stored in environment variable and
Spring reads them from operating system.

However, replace the *${JAVA_REST_DB}* with database name for your application.
*${JAVA_REST_USER}* with a username that has owner roles for this database,
*${JAVA_REST_PASSWORD}* the password for this user. The following configuration is
regarding MySQL drivers and Hibernate. 

In addition, in order to not create the database
each time it is running you could replace *spring.jpa.hibernate.ddl-auto=create* with
*spring.jpa.hibernate.ddl-auto=update*

The last configured variable is referred to JWT secret key for generating the token.

### Recommended Versions

| Recommended                 | Reference                                                                                                                                                    | Notes                                                                         |
|-----------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------|
| Amazon Correto JDK 11       | [Download](https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/downloads-list.html) | Java 11 is required for Spring Framework 5 and Spring Boot 2.7.               |
| MySQL Community Server 8.0+ | [Download](https://dev.mysql.com/downloads/installer/)                                                                                                         | For database server it is used the MySQL Community Server version 8 or older. |
| Gradle 8.6 or higher        |                                                                                                                       | Setting up Gradle or Maven when creating a new project with Intellij IDEA     |

### Dependencies

Using _Spring Boot 2.7_ and setting up the Gradle file the following dependencies with versions are installed:

1. _spring-boot-starter-data-jpa:2.7.15_
2. _spring-boot-starter-security:2.7.15_
3. _spring-boot-starter-web:2.7.15_