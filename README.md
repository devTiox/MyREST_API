# MyREST_API

## Requirements

- Java 21
- Docker

## Setup

Start PostgreSQL:

```bash
docker compose up -d
```

Database configuration used by the application:

- database: `myrestapi`
- user: `myrestapi_user`
- password: `task123`
- port: `5432`

## Run

Start the application:

```bash
./mvnw spring-boot:run
```

The API will be available at:

```text
http://localhost:8080
```

## Database Migrations

The project uses Liquibase. The schema is created automatically on application startup.

## Tests

Run tests with:

```bash
./mvnw test
```
