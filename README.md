# Finance Tracker

A simple API to track your finances, built with Spring Boot.

## Getting Started

### 1. Clone the Repository

Clone this repository to your local machine:

```bash
git clone https://github.com/VillegasMich/finance-tracker-SpringBoot.git
cd finance-tracker-SpringBoot
```

### 2. Update the application.properties file

Update the `application.properties` file with your database connection details:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/finance_tracker
spring.datasource.username=postgres
spring.datasource.password=password
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

## Database Schema

![finance-tracker-shema](https://github.com/user-attachments/assets/78d6ab25-2e65-4e51-8796-13c0539e6545)

## Endpoints

### Get

- `/api/users/get-all`: Get all users
- `/api/users/username/{username}`: Get user by username
- `/api/users/{id}`: Get user by id
- `api/users/total/{id}`: Get user total money
- `/api/incomes/get-all`: Get all incomes _(for production)_
- `/api/incomes/{id}`: Get income by id
- `/api/expenses/{id}`: Get expense by id
- `/api/incomes/user/{id}`: Get all incomes from a given user
- `/api/expenses/user/{id}`: Get all expenses from a given user
- `/api/expenses/get-all`: Get all expenses _(for production)_

### Post

- `/api/users/register`: Register a user
- `/api/users/find-by-credentials`: Find user by username or email and password
- `/api/incomes/register/{user_id}`: Register an income to a given user
- `/api/expenses/register/{user_id}`: Register an expense to a given user

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
- [x] Get user total money
- [x] Get all expenses from user
- [x] Get all incomes from user
- [ ] Update income from user
- [ ] Update expense from user
- [ ] Get all expenses from user by date (up / down)
- [ ] Get all incomes from user by date (up / down)
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
