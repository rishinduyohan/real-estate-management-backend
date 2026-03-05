# Real Estate Management System - Backend API

A RESTful backend API for a full-stack Real Estate Management System. Built with Spring Boot and secured with stateless JWT authentication, it serves an Angular + Tailwind CSS frontend with role-based access control for four user types.

---

## Tech Stack

- **Java 17** + **Spring Boot 3**
- **Spring Security** - Stateless JWT authentication
- **Spring Data JPA** / Hibernate - ORM layer
- **PostgreSQL 15** - Relational database with JSONB support
- **Apache Maven** - Build & dependency management

---

## Features

- **Authentication** - Secure registration and JWT-based login
- **Role-Based Access Control** - Four roles: `ADMIN`, `OWNER`, `CUSTOMER`
- **Property Listings** - Full CRUD, linked to owners and agents
- **Search & Filtering** - Filter by price, location, and listing status
- **Inquiry Management** - Customers submit inquiries; agents and owners manage responses
- **Admin Controls** - User management and platform-wide oversight

---

## Getting Started

### Prerequisites

- Java 21
- Maven 3.8+
- PostgreSQL 15+

### 1. Clone the repository

```bash
git clone https://github.com/rishinduyohan/real-estate-backend.git
cd real-estate-backend
git checkout dev
```

### 2. Set up the database

```sql
CREATE DATABASE real_estate_db;
CREATE USER realestate_user WITH ENCRYPTED PASSWORD 'your_password';
GRANT ALL PRIVILEGES ON DATABASE real_estate_db TO realestate_user;
```

### 3. Configure `application.properties`

```properties
# Server
server.port=8080

# Database
spring.datasource.url=jdbc:postgresql://localhost:5432/real_estate_db
spring.datasource.username=realestate_user
spring.datasource.password=your_password

# JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false

# JWT
app.jwt.secret=YOUR_SECRET_KEY
app.jwt.expiration-ms=86400000

# CORS
app.cors.allowed-origins=http://localhost:4200
```

> ⚠️ Do not commit credentials or your JWT secret to version control.

### 4. Run the application

```bash
mvn clean install
mvn spring-boot:run
```

API will be available at `http://localhost:8080/api/v1`

---

## API Endpoints

| Method | Endpoint | Access |
|--------|----------|--------|
| `POST` | `/auth/register` | Public |
| `POST` | `/auth/login` | Public |
| `GET` | `/properties` | Public |
| `GET` | `/properties/search` | Public |
| `POST` | `/properties` | ADMIN, OWNER |
| `PUT` | `/properties/{id}` | ADMIN, OWNER |
| `DELETE` | `/properties/{id}` | ADMIN, OWNER |
| `POST` | `/inquiries` | CUSTOMER |
| `GET` | `/inquiries` | ADMIN, OWNER |
| `GET` | `/admin/users` | ADMIN |

---

## Future Enhancements

- Refresh token support
- Email notifications on new inquiries
- Docker & CI/CD pipeline

---

## Developer

**Rishindu Peeris** — [@rishinduyohan](https://github.com/rishinduyohan)

---

## License

[MIT](LICENSE)
