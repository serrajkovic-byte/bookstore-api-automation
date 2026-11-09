# Bookstore API Automation
 
API test automation framework for the FakeRESTAPI Bookstore assessment.
 
## Tech stack
- Java 25
- Maven
- REST Assured 6.1.0
- JUnit Jupiter 6.1.3
- Maven Surefire Report Plugin 3.6.0
- GitHub Actions
 
## API under test
Base URL: https://fakerestapi.azurewebsites.net/api/v1
OpenAPI: https://fakerestapi.azurewebsites.net/swagger/v1/swagger.json
 
Covered resources:
- Books: GET list, GET by ID, POST, PUT, DELETE
- Authors: GET list, GET by ID, POST, PUT, DELETE
 
## Prerequisites
- JDK 25
- Maven 3.9+
- Git
 
Verify your setup:
```bash
java -version
mvn -version
git --version
```
 
## Clone the repository
```bash
git clone https://github.com/serrajkovic-byte/bookstore-api-automation.git
cd bookstore-api-automation
```
 
## Run all tests
```bash
mvn clean test
```
 
## Run a specific test class
```bash
mvn -Dtest=BooksApiTest test
mvn -Dtest=AuthorsApiTest test
```
 
## Generate HTML report
```bash
mvn surefire-report:report-only
```
Open: `target/site/surefire-report.html`
 
Raw Surefire results are available in `target/surefire-reports/`.
 
## Configure base URL
Default URL is configured in `ApiConfig`.
You can override it with:
```bash
mvn clean test -Dapi.baseUrl=https://fakerestapi.azurewebsites.net/api/v1
```
 
## Project structure
- `config` - base URL and shared request specification
- `clients` - reusable Books and Authors HTTP methods
- `testdata` - request payload factory
- `tests` - JUnit API test scenarios
- `.github/workflows` - CI pipeline
 
## Reporting and CI
GitHub Actions runs the test suite on push and pull requests to `main`.
The HTML and raw Surefire reports are uploaded as a workflow artifact.
 
## Important API limitation
FakeRESTAPI is a simulator. POST/PUT/DELETE responses simulate the expected operation,
but changes are not persisted as a real database transaction. Tests therefore validate
HTTP responses and payloads; they do not assert persistent CRUD state after each request.
 
## Test coverage
See the test matrix in this repository / assessment documentation.
