# Netflix Clone Microservices

This project is a microservice-based clone of Netflix, built with Spring Boot and Spring Cloud.

## Architecture

The system is composed of the following microservices:

1.  **Eureka Server** (Port 8761): Service Discovery Server.
2.  **API Gateway** (Port 8080): Public entry point for all requests.
3.  **Auth Service** (Port 8085): User authentication, registration, and JWT management.
4.  **Rating Service** (Port 8083): Manages user ratings for videos.
5.  **File Uploader Service** (Port 8082): Handles file uploads.
6.  **Email Service** (Port 8084): Sends emails (e.g., password reset).

## Prerequisites

- Java 17 or higher
- Maven
- PostgreSQL (for Auth and Rating services)
- SMTP Account (e.g., Gmail) for Email Service

## Environment Configuration

Before running the services, you must check the configuration files (`application.yaml`) or set the following environment variables.

### Email Service

The `EmailService` requires the following environment variables to send emails:

- `MAIL_USERNAME`: Your email address (e.g., `user@gmail.com`)
- `MAIL_PASSWORD`: Your email app password

### Database Configuration

Ensure PostgreSQL is running on `localhost:5432` with a database named `netflix_auth`, `netflix_rating` (or configure `application.yaml` in relevant services).
Default credentials assumed are `postgres` / `password`. Update these in `application.yaml` if different.

## Running the Services

You should run the services in the following order:

1.  **Eureka Server**

    ```bash
    cd EurekaServer
    mvn spring-boot:run
    ```

2.  **API Gateway**

    ```bash
    cd Gateway
    mvn spring-boot:run
    ```

3.  **Microservices** (Run each in a separate terminal)
    ```bash
    cd auth
    mvn spring-boot:run
    ```
    ```bash
    cd RatingService
    mvn spring-boot:run
    ```
    ```bash
    cd FIleUploderService
    mvn spring-boot:run
    ```
    ```bash
    cd EmailService
    # Ensure MAIL_USERNAME and MAIL_PASSWORD env vars are set
    mvn spring-boot:run
    ```

## API Endpoints (via Gateway)

All APIs are accessible via `http://localhost:8080`.

| Service    | Endpoint                         | Description            |
| :--------- | :------------------------------- | :--------------------- |
| **Auth**   | `POST /api/auth/register`        | Register a new user    |
| **Auth**   | `POST /api/auth/login`           | Login and get JWT      |
| **Auth**   | `POST /api/auth/forgot-password` | Request password reset |
| **Rating** | `POST /api/ratings`              | Rate a video           |
| **Rating** | `GET /api/ratings/video/{id}`    | Get video rating stats |
| **File**   | `POST /api/files/upload`         | Upload a file          |
| **Email**  | `POST /api/email/send`           | Send a generic email   |

## Postman Collection

A Postman collection is provided in `NetflixClone.postman_collection.json` to import and test all endpoints.
