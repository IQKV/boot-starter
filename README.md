# Spring Boot Starters

[![Build Status](https://drone.ujar.org/api/badges/ujar-org/ujar-boot-starter/status.svg?ref=refs/heads/main)](https://drone.ujar.org/ujar-org/ujar-boot-starter)

| Starter | Status                                                                                                                                                                                                                                                                        |
|---------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Restful | [![Quality Gate Status](https://sonarqube.ujar.org/api/project_badges/measure?project=ujar-org%3Aujar-boot-starter-restful&metric=alert_status&token=079b58510a190a61f593cc04e400943008c61418)](https://sonarqube.ujar.org/dashboard?id=ujar-org%3Aujar-boot-starter-restful) |
| Logbook | [![Quality Gate Status](https://sonarqube.ujar.org/api/project_badges/measure?project=ujar-org%3Aujar-boot-starter-logbook&metric=alert_status&token=53b7643b8f681909f9e2b23f23655b957f86cb78)](https://sonarqube.ujar.org/dashboard?id=ujar-org%3Aujar-boot-starter-logbook) |


## Code conventions

The code follows [Google Code Conventions](https://google.github.io/styleguide/javaguide.html) without exceptions. Code quality is measured by:
- [Sonarqube](https://sonarqube.ujar.org/dashboard?id=ujar-org%3Aujar-boot-starter-restful)
- [PMD](https://pmd.github.io/)
- [CheckStyle](https://checkstyle.sourceforge.io/)
- [SpotBugs](https://spotbugs.github.io/)

### Tests

This project has standard JUnit tests.
It is mandatory to keep test code coverage not below **80** percents and cover all business logic and edge cases.
