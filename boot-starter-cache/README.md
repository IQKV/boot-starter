# Spring Boot Starter Cache

## Overview

This starter provides auto-configuration for caching in Spring Boot applications. It simplifies the integration of caching capabilities to improve application performance by reducing database load and response times.

## Features

- Auto-configuration of cache managers
- Support for multiple cache providers (Redis, Caffeine, EhCache)
- Declarative caching with annotations
- Cache eviction and invalidation strategies
- Cache statistics and monitoring

## Getting Started

### Prerequisites

- Java 25 or higher
- Spring Boot 3.x

### Installation

Add the following dependency to your project:

```xml
<dependency>
    <groupId>com.iqkv</groupId>
    <artifactId>boot-starter-cache</artifactId>
    <version>${version}</version>
</dependency>
```

### Basic Configuration

Add the following properties to your `application.properties` or `application.yml`:

```properties
# Enable caching
spring.cache.type=caffeine
spring.cache.cache-names=users,products
```

## Usage Examples

### Caching Method Results

```java
@Service
public class UserService {

  @Cacheable(value = "users", key = "#id")
  public User getUserById(Long id) {
    // This result will be cached
    return userRepository.findById(id).orElse(null);
  }

  @CacheEvict(value = "users", key = "#user.id")
  public void updateUser(User user) {
    userRepository.save(user);
  }
}

```

### Conditional Caching

```java
@Cacheable(value = "products", key = "#id", condition = "#id > 10")
public Product getProductById(Long id) {
  return productRepository.findById(id).orElse(null);
}

```

## Advanced Configuration

For advanced configuration options, refer to the [Spring Cache documentation](https://docs.spring.io/spring-framework/docs/current/reference/html/integration.html#cache).

## License

This project is licensed under the terms specified in the root project.
