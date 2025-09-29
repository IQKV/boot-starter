# Spring Boot Starter Security

## Overview
This starter provides auto-configuration for security in Spring Boot applications. It simplifies the implementation of authentication, authorization, and other security features to protect your application.

## Features
- Auto-configuration of Spring Security
- JWT authentication support
- Role-based access control
- OAuth2 integration
- CSRF protection
- Session management
- Password encoding

## Getting Started

### Prerequisites
- Java 25 or higher
- Spring Boot 3.x

### Installation
```xml
<dependency>
    <groupId>com.iqkv</groupId>
    <artifactId>boot-starter-security</artifactId>
    <version>${version}</version>
</dependency>
```

### Basic Configuration
```properties
# Security Configuration
spring.security.user.name=admin
spring.security.user.password=admin
spring.security.user.roles=ADMIN

# JWT Configuration
app.security.jwt.secret=your-secret-key
app.security.jwt.expiration=86400000
```

## Usage Examples

### Securing Endpoints
```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/api/public/**").permitAll()
                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                .requestMatchers("/api/user/**").hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated()
            )
            .formLogin(withDefaults())
            .httpBasic(withDefaults());
        
        return http.build();
    }
}
```

### JWT Authentication
```java
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private JwtTokenProvider tokenProvider;
    
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
            )
        );
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);
        
        return ResponseEntity.ok(new JwtAuthResponse(jwt));
    }
}
```

## License
This project is licensed under the terms specified in the root project.
