# Real Estate Management Backend

A RESTful backend API for a real estate management system built with Spring Boot. It supports user authentication with JWT, property listings, and inquiry management.

## Tech Stack

- **Java** with **Spring Boot 3**
- **Spring Security** – authentication and authorization
- **JWT (jjwt 0.12.6)** – stateless token-based auth
- **Spring Data JPA** – data access layer
- **PostgreSQL** – relational database
- **Lombok** – boilerplate reduction
- **MapStruct** – DTO ↔ entity mapping
- **Maven** – build tool

## Prerequisites

- Java 17+
- Maven 3.8+
- PostgreSQL 14+

## Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/rishinduyohan/real-estate-management-backend.git
cd real-estate-management-backend
git checkout dev
```

### 2. Set up the database

Create a PostgreSQL database:

```sql
CREATE DATABASE real_estate_db;
```

### 3. Configure the application

Update `src/main/resources/application.yml` to use environment variables for sensitive values:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/real_estate_db
    username: ${DB_USERNAME}
    password: ${DB_PASSWORD}

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true

jwt:
  secret: ${JWT_SECRET}
```

Then set the corresponding environment variables before running the application:

```bash
export DB_USERNAME=your_db_username
export DB_PASSWORD=your_db_password
export JWT_SECRET=your_strong_random_secret   # use a long, randomly generated string in production
```

> **Security note:** Never commit real credentials or JWT secrets to source control.

### 4. Build and run

```bash
mvn clean install
mvn spring-boot:run
```

The server starts on `http://localhost:8080` by default.

## Project Structure

```
src/main/java/com/pvt/realestate/
├── controller/      # REST controllers
├── service/         # Business logic
├── repository/      # Spring Data JPA repositories
├── entity/          # JPA entities
├── dto/             # Data Transfer Objects
├── mapper/          # MapStruct mappers
├── security/        # JWT filter, security config, user details
└── Main.java        # Application entry point
```

## Key Features

- **JWT Authentication** – register, login, and secure endpoints with bearer tokens
- **BCrypt Password Encryption** – passwords are hashed before storage
- **User Management** – create and update user profiles
- **Property Listings** – manage real estate property records
- **Inquiry System** – users can submit and track inquiries on properties
- **CORS Support** – configured for cross-origin requests

## API Overview

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| POST | `/api/auth/register` | Register a new user | No |
| POST | `/api/auth/login` | Login and receive JWT | No |
| GET | `/api/users` | Get all users | Yes |
| GET | `/api/users/{id}` | Get user by ID | Yes |
| PUT | `/api/users/{id}` | Update user details | Yes |
| GET | `/api/properties` | Get all properties | Yes |
| POST | `/api/properties` | Create a property | Yes |
| GET | `/api/properties/{id}` | Get property by ID | Yes |
| PUT | `/api/properties/{id}` | Update a property | Yes |
| DELETE | `/api/properties/{id}` | Delete a property | Yes |
| GET | `/api/inquiries` | Get all inquiries | Yes |
| POST | `/api/inquiries` | Submit an inquiry | Yes |
| GET | `/api/inquiries/{id}` | Get inquiry by ID | Yes |

> Include the JWT token in requests as: `Authorization: Bearer <token>`

## Branch Strategy

| Branch | Purpose |
|--------|---------|
| `dev` | Main development branch (default) |
| `master` | Stable / production-ready releases |

## License

This project is open source. See [LICENSE](LICENSE) for details.
