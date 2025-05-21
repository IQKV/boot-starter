# ✨ Spring Boot Starters

## Code conventions

The code follows [Google Code Conventions](https://google.github.io/styleguide/javaguide.html). Code quality is measured by:

- [SonarQube](https://docs.sonarsource.com/)
- [PMD](https://pmd.github.io/)
- [CheckStyle](https://checkstyle.sourceforge.io/)
- [SpotBugs](https://spotbugs.github.io/)
- [Qulice](https://www.qulice.com/)

### Tests

This project contains a JUnit tests, Hamcrest matchers, Mockito test doubles, Wiremock stubs, etc.
The minimum percentage of code coverage required for the workflow to pass is **80%**.

> ### Versioning
>
> Project uses a three-segment [CalVer](https://calver.org/) scheme, with a short year in the major version slot, short month in the minor version slot, and micro/patch version in the third
> and final slot.
>
> ```
>  YY.MM.MICRO
> ```
>
> 1. **YY** - short year - 6, 16, 106
> 2. **MM** - short month - 1, 2 ... 11, 12
> 3. **MICRO** - "patch" segment
