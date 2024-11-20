# Finance Tracker

A simple API to track your finances, built with Spring Boot.

## Getting Started

### 1. Clone the Repository

Clone this repository to your local machine:

```bash
git clone https://github.com/VillegasMich/finance-tracker-SpringBoot.git
cd finance-tracker-SpringBoot
```

### 2. Update the application.properties file and the .env file

Check the `application.properties` file with your database connection details:

```properties
spring.datasource.url=${DATABASE_URL}
spring.datasource.username=${DATABASE_USERNAME}
spring.datasource.password=${DATABASE_PASSWORD}
```

And the `.env` file with your OpenAI API key and model:

```env
DATABASE_URL=jdbc:postgresql://localhost:5432/finance_tracker
DATABASE_USERNAME=root
DATABASE_PASSWORD=password
```

### 3. Run the Application

Run the application using Maven:

```bash
mvn spring-boot:run
```

### 4. Run the Backend

Run the backend using Maven:

```bash
mvn spring-boot:run
```

### 5. Check the API documentation ui

Check the API documentation ui at http://localhost:8080/swagger-ui/index.html when the server is running

## Database Schema

![finance-tracker-shema](https://github.com/user-attachments/assets/78d6ab25-2e65-4e51-8796-13c0539e6545)

## Endpoints

### Get

- `/api/users`: Get all users
- `/api/users/username/{username}`: Get user by username
- `/api/users/id/{id}`: Get user by id
- `api/users/total/{id}`: Get user total balance
- `/api/incomes`: Get all incomes _(for production)_
- `/api/incomes/id/{id}`: Get income by id
- `/api/expenses/id/{id}`: Get expense by id
- `/api/incomes/user/{id}`: Get all incomes from a given user
- `/api/expenses/user/{id}`: Get all expenses from a given user
- `/api/expenses`: Get all expenses _(for production)_
- `/api/incomes/user/{id}/total`: Get total income from a given user
- `/api/expenses/user/{id}/total`: Get total expense from a given user
- `/api/incomes/user/{id}/date-new`: Get all incomes from a given user by date (up / down)
- `/api/expenses/user/{id}/date-new`: Get all expenses from a given user by date (up / down)
- `/api/incomes/user/{id}/date-old`: Get all incomes from a given user by date (down / up)
- `/api/expenses/user/{id}/date-old`: Get all expenses from a given user by date (down / up)

### Post

- `/api/users/register`: Register a user
- `/api/incomes/register`: Register an income to a given user
- `/api/expenses/register`: Register an expense to a given user

### Delete

- `/api/delete/users/{id}`: Delete user by id
- `/api/delete/incomes/{id}`: Delete income by id
- `/api/delete/expenses/{id}`: Delete expense by id

## Dependencies

- `spring-boot-starter-web`
- `spring-boot-starter-data-jpa`
- `spring-boot-starter-security`
- `postgresql`
- `org.springframework.boot:spring-boot-devtools`
- `org.springframework.boot:spring-boot-docker-compose`
- `org.springframework.boot:spring-boot-starter-test`
- `org.springframework.boot:spring-boot-testcontainers`
- `org.springframework.boot:spring-security-test`
- `org.testcontainers:junit-jupiter`
- `org.testcontainers:postgresql`
- `com.h2database:h2`
- `org.springframework.boot:spring-boot-starter-data-jpa`
- `springdoc-openapi-starter-webmvc-ui`

## Requirements

- Java 21+
- Maven 3.8+
- Docker
- Docker Compose

## Features

- [x] Register a user
- [x] Get all users
- [x] Get user by username
- [x] Update user by id
- [x] Get user by credentials
- [x] Get user total balance
- [x] Get all expenses from user
- [x] Get all incomes from user
- [x] Get total income from user
- [x] Get total expense from user
- [ ] Update income from user
- [ ] Update expense from user
- [x] Get all expenses from user by date (up / down)
- [x] Get all expenses from user by date (down / up)
- [x] Get all incomes from user by date (up / down)
- [x] Get all incomes from user by date (down / up)
- [ ] Get greater expense from user
- [ ] Get greater income from user
- [ ] Get less expense from user
- [ ] Get less income from user
- [x] Delete user by id
- [x] Delete expense from user
- [x] Delete income from user

## Ideas

- Manage a total table in database?
- Implement another algorithm to calculate total
