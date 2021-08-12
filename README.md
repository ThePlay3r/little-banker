# Little Banker

Simple RESTful service, that mimics small banking enterprises written in Java.

# Technology used
- [Gradle](https://gradle.org/)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Lombok](https://projectlombok.org/)

# Configuration

Simple configuration is located in `src/resources/application.yaml`

# Services

Servlet context path = `/api/v1`

| HTTP METHOD | PATH | USAGE |
| ----------- | ---- | ----- |
| **GET** | `/customer/`  | List of all Customers |
| **POST** | `/customer/` | Creation of new Customer |
| **GET** | `/customer/{customerId}` | Listing a specific Customer by ID |
| **PUT** | `/customer/{customerId}` | Changing the Customer information |
| **DELETE** | `/customer/{customerId}` | Deleting a Customer |
| **GET** | `/customer/{customerId}/account/` | List of Customer's Accounts |
| **POST** | `/customer/{customerId}/account/` | Creation of new Account |
| **PUT** | `/customer/{customerId}/account/{accountId}` | Changing the Account information |
| **DELETE** | `/customer/{customerId}/account/{accountId}` | Deleting an Account |
| **GET** | `/transaction/` | List of all transactions |
| **POST** | `/transaction/` | Creation of a new transaction |
| **GET** | `/transaction/search[?amount][?iban][?message]` | Searching in transactions |
