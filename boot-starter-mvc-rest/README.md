# Spring Boot Starter MVC REST

## Overview

This starter provides auto-configuration for building RESTful APIs in Spring Boot applications. It includes essential components for creating robust, well-structured REST endpoints with
proper error handling and documentation.

## Features

- Auto-configuration of Spring MVC for REST APIs
- Standardized response formats
- Global exception handling
- Request validation
- API documentation with OpenAPI/Swagger
- CORS configuration

## Getting Started

### Prerequisites

- Java 21 or higher
- Spring Boot 3.x

### Installation

```xml
<dependency>
    <groupId>com.iqkv</groupId>
    <artifactId>boot-starter-mvc-rest</artifactId>
    <version>${version}</version>
</dependency>
```

### Basic Configuration

```properties
# API Documentation
springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.path=/swagger-ui.html

# CORS Configuration
spring.mvc.cors.allowed-origins=*
spring.mvc.cors.allowed-methods=GET,POST,PUT,DELETE
```

## Usage Examples

### Creating a REST Controller

```java
@RestController
@RequestMapping("/api/users")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping("/{id}")
  public ResponseEntity<User> getUserById(@PathVariable Long id) {
    return ResponseEntity.ok(userService.findById(id));
  }

  @PostMapping
  public ResponseEntity<User> createUser(@Valid @RequestBody UserDto userDto) {
    User createdUser = userService.create(userDto);
    return ResponseEntity.created(URI.create("/api/users/" + createdUser.getId())).body(createdUser);
  }
}

```

## License

This project is licensed under the terms specified in the root project.
