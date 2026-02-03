# SPRINT THREE - Product Inventory API
## Project Duration: February 1, 2026 – February 4, 2026
### Overview
The Product Inventory System is a production-grade RESTful API designed to showcase API Best Practices and Standardization. While previous sprints focused on architecture and database persistence, this project focuses on the contract between the client and the server.

It implements a strict adherence to REST maturity models, utilizing proper HTTP verbs (including PATCH for partial updates), comprehensive input validation, and a standardized JSON response envelope to ensure consistent communication.

### Concepts & Architecture
This project refines the API layer to meet industry standards for maintainability and ease of consumption:

#### 1. REST Maturity & API Versioning
The API is designed to be predictable and future-proof:
```
a. URI Versioning: All endpoints are prefixed with /api/v1/ to allow for future breaking changes without disrupting current clients.
b. Semantic HTTP Verbs:
   - GET: Retrieve resources (idempotent).
   - POST: Create new resources (201 Created).
   - PUT: Full resource replacement.
   - PATCH: Partial updates (specifically used for stock adjustments).
   - DELETE: Removal of resources (204 No Content).
```
#### 2. Robust Error Handling & Validation
Moving away from default 500 errors to informative, client-friendly error messages:
```
a. Global Exception Handler (@RestControllerAdvice): Captures exceptions across the entire application (e.g., ProductNotFound, InsufficientStock) and transforms them into standard error responses.
b. Jakarta Validation: DTOs use annotations like @Min, @NotNull, and @NotBlank.
c. Field-Level Errors: Validation failures return specific field names and error messages, aiding frontend debugging.
```
#### 3. Standardized Response Wrapper
To prevent the client from parsing different JSON structures for success vs. failure, every response is wrapped in a unified ApiResponse object:
```
JSON
{
  "success": true,
  "message": "Product created successfully",
  "data": { ... },
  "timestamp": "2026-02-02T10:00:00"
}
```
#### 4. API Documentation (OpenAPI / Swagger)
Self-documenting code using springdoc-openapi:
```
a. Swagger UI: Provides an interactive interface at /swagger-ui.html to test endpoints without external tools like Postman.
b. @Operation & @Tag: Used to add descriptive metadata to endpoints and controllers, making the API definition clear to other developers.
```
### Tech Stack
Language: Java 21

Framework: Spring Boot 3.2+ (Web, Validation)

Documentation: SpringDoc OpenAPI (Swagger UI)

Data Store: In-Memory ConcurrentHashMap (Focus on Thread Safety & API Design over DB persistence)

Build Tool: Maven

Utilities: Lombok
