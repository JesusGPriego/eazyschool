# Eazy School Management Application

This application is a school management application built using Spring Boot. It provides basic features for managing students, teachers and courses.

## Features

* Manage students
* Manage courses
* Assign students to courses
* Generate reports

## How to run

1. Clone the repository
2. Run `mvn clean install` to build the application
3. Run `java -jar target/eazyschool-0.0.1-SNAPSHOT.jar` to start the application
4. Open a web browser and navigate to `http://localhost:8081/` to access the application

## Running the Application with Different Profiles

The Eazy School Management Application can be run using different Spring profiles to accommodate various configurations and environments. Below are the instructions for running the application with specific profiles using Maven:

### Available Profiles

1. **dev**: The dev profile that is used if no other profile is specified.
2. **uat**: The uat profile.
3. **prod**: The production profile, which is configured for deployment in a production environment.

### Running with a Specific Profile

To run the application with a specific profile, you can use the `-Dspring.profiles.active` option with the `mvn spring-boot:run` command. Here are examples for each profile:

- **Default Profile**:
  ```sh
  mvn spring-boot:run
  ```

- **Development Profile**:
  ```sh
  mvn spring-boot:run -Dspring.profiles.active=dev
  ```

- **Production Profile**:
  ```sh
  mvn spring-boot:run -Dspring.profiles.active=prod
  ```

These commands will start the application with configuration settings specific to the chosen profile.

## Technologies used

* Spring Boot
* Spring Data JPA
* Hibernate
* Thymeleaf
* Bootstrap

## License

This application is licensed under the MIT License. See the LICENSE file for details.
