# Central Queue

Generic queue management system

## Usage

### Build

```console
$ ./mvnw clean install
```

### Run

```console
$ ./mvnw spring-boot:run -pl config
```

#### API Tests

While running:

```console
$ ./mvnw test -Dtest=KarateRunner -pl config
```

## Design

- [Robert C. Martin - Clean Architecture](https://vimeo.com/43612849)
- [Real Life Clean Architecture](https://www.slideshare.net/mattiabattiston/real-life-clean-architecture-61242830)
  - [Clean Architecture Example (Java): Example of what clean architecture would look like](https://github.com/mattia-battiston/clean-architecture-example)
- [A example of clean architecture in Java 8 and Spring Boot 2.0](https://github.com/eliostvs/clean-architecture-delivery-example)
