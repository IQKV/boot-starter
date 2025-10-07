# Spring Boot Starter Mail

## Overview

This starter provides auto-configuration for email functionality in Spring Boot applications. It simplifies sending emails with templates, attachments, and various mail providers.

## Features

- Auto-configuration of JavaMailSender
- Support for HTML and template-based emails
- Attachment handling
- Multiple mail provider configurations
- Asynchronous email sending

## Getting Started

### Prerequisites

- Java 25 or higher
- Spring Boot 3.x

### Installation

```xml
<dependency>
    <groupId>com.iqkv</groupId>
    <artifactId>boot-starter-mail</artifactId>
    <version>${version}</version>
</dependency>
```

### Basic Configuration

```properties
spring.mail.host=smtp.example.com
spring.mail.port=587
spring.mail.username=your-username
spring.mail.password=your-password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

## Usage Examples

### Sending Simple Email

```java
@Autowired
private JavaMailSender emailSender;

public void sendSimpleMessage(String to, String subject, String text) {
  SimpleMailMessage message = new SimpleMailMessage();
  message.setTo(to);
  message.setSubject(subject);
  message.setText(text);
  emailSender.send(message);
}

```

### Sending HTML Email with Attachment

```java
@Autowired
private JavaMailSender emailSender;

public void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment) {
  MimeMessage message = emailSender.createMimeMessage();
  MimeMessageHelper helper = new MimeMessageHelper(message, true);

  helper.setTo(to);
  helper.setSubject(subject);
  helper.setText(text, true);

  FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
  helper.addAttachment("Invoice.pdf", file);

  emailSender.send(message);
}

```

## License

This project is licensed under the terms specified in the root project.
