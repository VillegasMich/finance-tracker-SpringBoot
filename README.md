# Finance Tracker

A simple API to track your finances, built with Spring Boot.

## Getting Started

Run the following command to start the application:

mvn clean package exec:java

## Database Schema

![finance-tracker-shema](https://github.com/user-attachments/assets/78d6ab25-2e65-4e51-8796-13c0539e6545)

## Endpoints

### Get

- `/api/users/get-all`: Get all users
- `/api/users/username/{username}`: Get user by username
- `/api/users/{id}`: Get user by id
- `api/users/total/{id}`: Get user total money
- `/api/incomes/get-all`: Get all incomes _(for production)_
- `/api/expenses/get-all`: Get all expenses _(for production)_

### Post

- `/api/users/register`: Register a user
- `/api/users/find-by-credentials`: Find user by username or email and password

### Delete

- `/api/users/{id}`: Delete user by id

## Dependencies

- `spring-boot-starter-web` (https://start.spring.io/)

## Requirements

- Java 21+
- Maven 3.8+
- Docker
- PostgreSQL
- Docker Compose

## Features

- [x] Register a user
- [x] Get all users
- [x] Get user by username
- [x] Update user by id
- [x] Get user by credentials
- [x] Get user total money
- [ ] Get all expenses from user
- [ ] Get all incomes from user
- [ ] Update income from user
- [ ] Update expense from user
- [ ] Get all expenses from user by date (up / down)
- [ ] Get all incomes from user by date (up / down)
- [ ] Get greater expense from user
- [ ] Get greater income from user
- [ ] Get less expense from user
- [ ] Get less income from user
- [x] Delete user by id
- [ ] Delete expense from user
- [ ] Delete income from user
- [ ] Delete all expenses from user
- [ ] Delete all incomes from user

## Ideas

- Manage a total table in database?
