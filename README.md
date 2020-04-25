# Central Queue

## Usage

```console
$ ./mvnw clean install
$ ./mvnw spring-boot:run -pl config -Dspring-boot.run.profiles=test
```

## References

### Clean Architecture

- https://www.slideshare.net/mattiabattiston/real-life-clean-architecture-61242830
  - https://github.com/mattia-battiston/clean-architecture-example
- https://jmgarridopaz.github.io/content/hexagonalarchitecture.html
- https://pusher.com/tutorials/clean-architecture-introduction
- https://github.com/eliostvs/clean-architecture-delivery-example
  - https://softwareengineering.stackexchange.com/a/373524
- [Robert C. Martin - Clean Architecture](https://vimeo.com/43612849)
- [Why you need Use Cases/Interactors](https://proandroiddev.com/why-you-need-use-cases-interactors-142e8a6fe576)
- https://hackernoon.com/clean-architecture-example-in-kotlin-9f23169219be

#### Builder Pattern

- https://www.geeksforgeeks.org/builder-pattern-in-java/
- https://www.journaldev.com/1425/builder-design-pattern-in-java

#### Spring-Boot

- https://medium.com/slalom-build/clean-architecture-with-java-11-f78bba431041
  - https://github.com/carlphilipp/clean-architecture-example
- https://reflectoring.io/java-components-clean-boundaries/
- https://imasters.com.br/back-end/introducao-clean-architecture
- https://medium.com/@icarovictor/the-clean-architecture-54df8a46dba1

### JSON

- https://jsonschema.net/

### Maven

- http://maven.apache.org/maven-ci-friendly.html
  - https://jeanchristophegay.com/en/posts/maven-unique-version-multi-modules-build/
- https://www.baeldung.com/maven-java-version
  - https://medium.com/criciumadev/its-time-migrating-to-java-11-5eb3868354f9
- https://mkyong.com/maven/how-to-create-a-java-project-with-maven/
  - https://github.com/mkyong/maven-examples

### Test

#### jUnit5

- https://www.baeldung.com/junit-5
- https://www.baeldung.com/mockito-junit-5-extension
- https://mkyong.com/spring-boot/spring-boot-junit-5-mockito/
- https://www.petrikainulainen.net/programming/testing/junit-5-tutorial-writing-nested-tests/
- http://sangsoonam.github.io/2019/02/04/mockito-doreturn-vs-thenreturn.html
- https://www.baeldung.com/junit-assert-exception
- https://www.baeldung.com/mockito-verify

### Java

#### Dependency Injection

- https://www.vogella.com/tutorials/DependencyInjection/article.html
- https://javarevisited.blogspot.com/2017/04/difference-between-autowired-and-inject-annotation-in-spring-framework.html
- https://www.baeldung.com/spring-annotations-resource-inject-autowire
- https://mvnrepository.com/artifact/javax.inject/javax.inject/1

#### Modules

- https://dzone.com/articles/java-9-module-services
- https://www.baeldung.com/project-jigsaw-java-modularity
- https://medium.com/slalom-build/clean-architecture-with-java-11-f78bba431041
- https://www.oracle.com/corporate/features/understanding-java-9-modules.html

#### ModelMapper

- https://github.com/modelmapper/modelmapper/issues/265

### Spring-Boot

- https://start.spring.io/
- https://spring.io/guides/gs/spring-boot/
- https://docs.spring.io/spring-boot/docs/current/reference/html/getting-started.html
- https://github.com/eugenp/tutorials/blob/master/spring-boot-rest/src/main/java/com/baeldung/springpagination/controller/PostRestController.java

#### Maven

- https://docs.spring.io/spring-boot/docs/current/maven-plugin/examples/run-profiles.html

#### Beans

- https://docs.spring.io/spring-javaconfig/docs/1.0.0.m3/reference/html/creating-bean-definitions.html

#### Rest

- https://spring.io/guides/tutorials/rest/
- https://www.baeldung.com/rest-with-spring-series
  - https://www.baeldung.com/entity-to-and-from-dto-for-a-java-spring-application

#### Controller

- https://www.baeldung.com/spring-controller-vs-restcontroller

#### Logging

- https://stackoverflow.com/a/55338237
  - https://dzone.com/articles/logger-injection-with-springs-injectionpoint

#### JSON

- https://www.baeldung.com/jackson-deserialize-immutable-objects
- https://www.baeldung.com/jackson-map
