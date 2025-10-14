# Spring Boot Starter Kafka

## Overview

This starter provides auto-configuration for Apache Kafka messaging in Spring Boot applications. It simplifies the integration of event streaming capabilities for building distributed,
scalable, and fault-tolerant applications.

## Features

- Auto-configuration of Kafka producers and consumers
- Support for Kafka Streams API
- Message serialization and deserialization
- Error handling and retry mechanisms
- Monitoring and metrics integration

## Getting Started

### Prerequisites

- Java 25 or higher
- Spring Boot 3.x

### Installation

Add the following dependency to your project:

```xml
<dependency>
    <groupId>com.iqkv</groupId>
    <artifactId>boot-starter-kafka</artifactId>
    <version>${version}</version>
</dependency>
```

### Basic Configuration

Add the following properties to your `application.properties` or `application.yml`:

```properties
# Kafka Connection
spring.kafka.bootstrap-servers=localhost:9092
spring.kafka.consumer.group-id=my-group
```

## Usage Examples

### Producing Messages

```java
@Autowired
private KafkaTemplate<String, String> kafkaTemplate;

public void sendMessage(String message) {
  kafkaTemplate.send("topic-name", message);
}

```

### Consuming Messages

```java
@KafkaListener(topics = "topic-name", groupId = "my-group")
public void listen(String message) {
  // Process the message
  System.out.println("Received message: " + message);
}

```

## License

This project is licensed under the terms specified in the root project.
