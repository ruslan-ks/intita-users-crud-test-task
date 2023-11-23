## Users CRUD - Spring test task

### Starting the app

Run `./gradlew bootRun` or use your IDE

**Spring Boot Docker Compose support is used, so you need to have Docker installed to run this app**

### Database

Postgres container is created on startup via Spring Boot Docker Compose support

Flyway migrations are used to create tables and insert demo rows

### Implemented endpoints

GET /users - get users page
GET /users/{id} - get user by id
POST /users - create user
PATCH /users/{id} - patch user by id
DELETE /users/{id} - delete user by id
