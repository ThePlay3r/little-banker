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

# JSON Object Samples

## Customer

```json
{
  "name": "Petr",
  "surname": "Novak",
  "sex": "MALE",
  "nationality": "Czech",
  "birthDate": "2000-01-01",
  "cardNumber": "5360600185980299",
  "cardIssueDate": "2020-06-01",
  "cardExpiryDate": "2023-06-01"
}
```

## Account

```json
{
  "iban": "CZ6508000000192000145399",
  "currency": "CZK",
  "balance": 1500.00
}
```

## Transaction

```json
{
  "amount": 100.00,
  "creditorIBAN": "CZ6508000000192000145399",
  "debtorIBAN": "CZ6508000000192000145400",
  "message": "Gift of 100CZK"
}
```