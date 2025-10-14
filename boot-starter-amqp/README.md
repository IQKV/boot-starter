# Spring Boot Starter AMQP

## Overview

This starter provides auto-configuration for RabbitMQ messaging in Spring Boot applications. It simplifies the integration of asynchronous messaging capabilities using the Advanced Message
Queuing Protocol (AMQP).

## Features

- Auto-configuration of RabbitMQ connection factories
- Simplified message producer and consumer setup
- Support for message conversion and routing
- Error handling and retry mechanisms
- Dead letter queue configuration

## Getting Started

### Prerequisites

- Java 25 or higher
- Spring Boot 3.x

### Installation

Add the following dependency to your project:

```xml
<dependency>
    <groupId>com.iqkv</groupId>
    <artifactId>boot-starter-amqp</artifactId>
    <version>${version}</version>
</dependency>
```

### Basic Configuration

Add the following properties to your `application.properties` or `application.yml`:

```properties
# RabbitMQ Connection
spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672
spring.rabbitmq.username=guest
spring.rabbitmq.password=guest
```

## Usage Examples

### Sending Messages

```java
@Autowired
private RabbitTemplate rabbitTemplate;

public void sendMessage(String message) {
  rabbitTemplate.convertAndSend("exchange-name", "routing-key", message);
}

```

### Receiving Messages

```java
@RabbitListener(queues = "queue-name")
public void receiveMessage(String message) {
  // Process the message
  System.out.println("Received message: " + message);
}

```

## Advanced Configuration

For advanced configuration options, refer to the [Spring AMQP documentation](https://docs.spring.io/spring-amqp/docs/current/reference/html/).

## License

This project is licensed under the terms specified in the root project.
