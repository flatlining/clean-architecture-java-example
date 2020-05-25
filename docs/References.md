# References

<!-- vscode-markdown-toc -->
* [Architecture](#Architecture)
	* [Clean Architecture](#CleanArchitecture)
		* [Spring-Boot](#Spring-Boot)
	* [Builder Pattern](#BuilderPattern)
* [JSON](#JSON)
* [Maven](#Maven)
* [Test](#Test)
	* [jUnit5](#jUnit5)
	* [Karate](#Karate)
* [Java](#Java)
	* [Dependency Injection](#DependencyInjection)
	* [Lombok](#Lombok)
	* [Modules](#Modules)
	* [ModelMapper](#ModelMapper)
	* [Time](#Time)
* [Spring-Boot](#Spring-Boot-1)
	* [Maven](#Maven-1)
	* [Beans](#Beans)
	* [Application Configuration](#ApplicationConfiguration)
	* [Rest](#Rest)
	* [Controller](#Controller)
	* [Logging](#Logging)
	* [JSON](#JSON-1)
	* [Test](#Test-1)
	* [Database](#Database)
		* [SQLite](#SQLite)
		* [H2](#H2)

<!-- vscode-markdown-toc-config
	numbering=false
	autoSave=true
	/vscode-markdown-toc-config -->
<!-- /vscode-markdown-toc -->

## <a name='Architecture'></a>Architecture

### <a name='CleanArchitecture'></a>Clean Architecture

- [Real Life Clean Architecture](https://www.slideshare.net/mattiabattiston/real-life-clean-architecture-61242830)
  - https://github.com/mattia-battiston/clean-architecture-example
- [Ports and Adapters Pattern](https://jmgarridopaz.github.io/content/hexagonalarchitecture.html)
- [Clean Architecture For The Rest Of Us](https://pusher.com/tutorials/clean-architecture-introduction)
- [Implementing a REST API in a Clean Architecture](https://softwareengineering.stackexchange.com/a/373524)
  - https://github.com/eliostvs/clean-architecture-delivery-example
- [Robert C. Martin - Clean Architecture](https://vimeo.com/43612849)
- [Why you need Use Cases/Interactors](https://proandroiddev.com/why-you-need-use-cases-interactors-142e8a6fe576)
- [Clean Architecture Example in Kotlin](https://hackernoon.com/clean-architecture-example-in-kotlin-9f23169219be)
- [Clean Architecture Tutorial for Android: Getting Started](https://www.raywenderlich.com/3595916-clean-architecture-tutorial-for-android-getting-started)
  - https://github.com/dmilicic/Android-Clean-Boilerplate

#### <a name='Spring-Boot'></a>Spring-Boot

- [Clean Architecture with Java 11](https://medium.com/slalom-build/clean-architecture-with-java-11-f78bba431041)
  - https://github.com/carlphilipp/clean-architecture-example
- [Clean Architecture Boundaries with Spring Boot and ArchUnit](https://reflectoring.io/java-components-clean-boundaries/)
- [Introdução a Clean Architecture](https://imasters.com.br/back-end/introducao-clean-architecture)
- [The clean Architecture](https://medium.com/@icarovictor/the-clean-architecture-54df8a46dba1)

### <a name='BuilderPattern'></a>Builder Pattern

- [Builder Pattern in java](https://www.geeksforgeeks.org/builder-pattern-in-java/)
- [Builder Design Pattern in Java](https://www.journaldev.com/1425/builder-design-pattern-in-java)

## <a name='JSON'></a>JSON

- [JSONschema.net](https://jsonschema.net/)

## <a name='Maven'></a>Maven

- [Maven CI Friendly Versions](http://maven.apache.org/maven-ci-friendly.html)
  - [Use a unique property to set Maven version](https://jeanchristophegay.com/en/posts/maven-unique-version-multi-modules-build/)
- [Setting the Java Version in Maven](https://www.baeldung.com/maven-java-version)
  - [It’s time! Migrating to Java 11](https://medium.com/criciumadev/its-time-migrating-to-java-11-5eb3868354f9)
- [Maven – How to create a Java project](https://mkyong.com/maven/how-to-create-a-java-project-with-maven/)
  - https://github.com/mkyong/maven-examples

To print a [flat](http://www.janosgyerik.com/print-a-flat-list-of-dependencies-of-a-maven-project/) dependency tree:

```console
$ ./mvnw dependency:list | sed -ne s/..........// -e /patterntoexclude/d -e s/:compile//p -e s/:runtime//p | sort | uniq
```

## <a name='Test'></a>Test

- [The Practical Test Pyramid](https://martinfowler.com/articles/practical-test-pyramid.html)

### <a name='jUnit5'></a>jUnit5

- [A Guide to JUnit 5](https://www.baeldung.com/junit-5)
- [Mockito and JUnit 5 – Using ExtendWith](https://www.baeldung.com/mockito-junit-5-extension)
- [Spring Boot + JUnit 5 + Mockito](https://mkyong.com/spring-boot/spring-boot-junit-5-mockito/)
- [JUnit 5 Tutorial: Writing Nested Tests](https://www.petrikainulainen.net/programming/testing/junit-5-tutorial-writing-nested-tests/)
- [Mockito: doReturn vs thenReturn](http://sangsoonam.github.io/2019/02/04/mockito-doreturn-vs-thenreturn.html)
- [Assert an Exception is Thrown in JUnit 4 and 5](https://www.baeldung.com/junit-assert-exception)
- [Mockito Verify Cookbook](https://www.baeldung.com/mockito-verify)

### <a name='Karate'></a>Karate

- https://github.com/intuit/karate
- https://github.com/intuit/karate/tree/master/karate-demo
- https://github.com/intuit/karate/tree/master/karate-mock-servlet
- https://www.baeldung.com/karate-rest-api-testing
- https://medium.com/cwi-software/karate-dsl-automatizando-testes-de-api-de-forma-simples-3624ab230198

## <a name='Java'></a>Java

### <a name='DependencyInjection'></a>Dependency Injection

- [Using dependency injection in Java - Introduction - Tutorial](https://www.vogella.com/tutorials/DependencyInjection/article.html)
- [Difference between @Autowired and @Inject annotation in Spring?](https://javarevisited.blogspot.com/2017/04/difference-between-autowired-and-inject-annotation-in-spring-framework.html)
- [Wiring in Spring: @Autowired, @Resource and @Inject](https://www.baeldung.com/spring-annotations-resource-inject-autowire)
- [Javax Inject](https://mvnrepository.com/artifact/javax.inject/javax.inject/1)

### <a name='Lombok'></a>Lombok

- [Project Lombok > Maven](https://projectlombok.org/setup/maven)
  - https://github.com/MCMicS/simple-lombok
  - [How to add Lombok to a modular Java 11 micro service](https://medium.com/@Leejjon_net/youll-have-this-problems-when-you-add-lombok-to-a-modular-java-11-micro-service-832f55911bc5)
- [Setting up Lombok with Eclipse and Intellij](https://www.baeldung.com/lombok-ide)
- [JSONSerialize and JSONDeserialize with Lombok custom deserialization builders](http://errataobscura.blogspot.com/2019/03/jsonserialize-and-jsondeserialize-with.html)

### <a name='Modules'></a>Modules

- [Java 9 Module Services](https://dzone.com/articles/java-9-module-services)
- [Introduction to Project Jigsaw](https://www.baeldung.com/project-jigsaw-java-modularity)
- [Understanding Java 9 Modules](https://www.oracle.com/corporate/features/understanding-java-9-modules.html)

### <a name='ModelMapper'></a>ModelMapper

- [ModelMapper should support Builder Pattern](https://github.com/modelmapper/modelmapper/issues/265)

### <a name='Time'></a>Time

- [The 5 laws of API dates and times](http://apiux.com/2013/03/20/5-laws-api-dates-and-times/)
- [Add a Timezone to LocalDateTime with ZonedDateTime in Java 8](https://www.codebyamir.com/blog/add-a-timezone-to-localdatetime-with-zoneddatetime-in-java-8)
- [What's the difference between Instant and LocalDateTime?](https://stackoverflow.com/a/32443004)
- [Based on thousands of APIs, what is the best approaches and format for handling timezone, timestamps, and datetime in APIs and Apps](https://www.moesif.com/blog/technical/timestamp/manage-datetime-timestamp-timezones-in-api/)
- [Convert Date to ISO 8601 String in Java](https://mincong.io/2017/02/16/convert-date-to-string-in-java/)

## <a name='Spring-Boot-1'></a>Spring-Boot

- [Spring Initializr](https://start.spring.io/)
- [Building an Application with Spring Boot](https://spring.io/guides/gs/spring-boot/)
- [Spring Boot: Getting Started](https://docs.spring.io/spring-boot/docs/current/reference/html/getting-started.html)
- https://github.com/eugenp/tutorials/blob/master/spring-boot-rest/src/main/java/com/baeldung/springpagination/controller/PostRestController.java

### <a name='Maven-1'></a>Maven

- [Spring Boot Maven Plugin](https://docs.spring.io/spring-boot/docs/current/maven-plugin/examples/run-profiles.html)

### <a name='Beans'></a>Beans

- [Creating and using bean definitions](https://docs.spring.io/spring-javaconfig/docs/1.0.0.m3/reference/html/creating-bean-definitions.html)

### <a name='ApplicationConfiguration'></a>Application Configuration

- [Spring boot application.properties maven multi-module projects](https://stackoverflow.com/a/33298125)

### <a name='Rest'></a>Rest

- [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
- [REST with Spring Tutorial](https://www.baeldung.com/rest-with-spring-series)
  - [Entity To DTO Conversion for a Spring REST API](https://www.baeldung.com/entity-to-and-from-dto-for-a-java-spring-application)

### <a name='Controller'></a>Controller

- [The Spring @Controller and @RestController Annotations](https://www.baeldung.com/spring-controller-vs-restcontroller)

### <a name='Logging'></a>Logging

- [How do I inject a logger into a field in the sample spring boot application?](https://stackoverflow.com/a/55338237)
  - [Logger Injection With Spring’s InjectionPoint](https://dzone.com/articles/logger-injection-with-springs-injectionpoint)
- [Inject loggers in Spring | Java or Kotlin](https://medium.com/simars/inject-loggers-in-spring-java-or-kotlin-87162d02d068)

### <a name='JSON-1'></a>JSON

- [Deserialize Immutable Objects with Jackson](https://www.baeldung.com/jackson-deserialize-immutable-objects)
- [Map Serialization and Deserialization with Jackson](https://www.baeldung.com/jackson-map)
- [How to Handle Java 8 Dates and Time with Jackson in Spring Boot (JSR-310)](https://codeboje.de/jackson-java-8-datetime-handling/)
- https://github.com/FasterXML/jackson-modules-java8

### <a name='Test-1'></a>Test

- [Testing the Web Layer](https://spring.io/guides/gs/testing-web/)
- [Unit Testing with Spring Boot](https://reflectoring.io/unit-testing-spring-boot/)
- [Test Your Spring Boot Applications with JUnit 5](https://developer.okta.com/blog/2019/03/28/test-java-spring-boot-junit5)
- [Mockito.mock() vs @Mock vs @MockBean](https://www.baeldung.com/java-spring-mockito-mock-mockbean)
- [Spring boot @MockBean Example](https://howtodoinjava.com/spring-boot2/testing/spring-mockbean-annotation/)
- [Mockito Argument Matchers – any(), eq()](https://www.journaldev.com/21876/mockito-argument-matchers-any-eq)
- [Mockito – Using Spies](https://www.baeldung.com/mockito-spy)
- https://github.com/spring-projects/spring-framework/blob/master/spring-test/src/test/java/org/springframework/test/web/servlet/samples/standalone/AsyncTests.java

### <a name='Database'></a>Database

#### <a name='SQLite'></a>SQLite

- https://www.sqlitetutorial.net/sqlite-java/
- https://fonini.github.io/2010/02/18/usando-sqlite-com-java/
- https://receitasdecodigo.com.br/java/usando-sqlite-em-java

#### <a name='H2'></a>H2

- https://www.baeldung.com/spring-boot-h2-database
- https://h2database.com/html/tutorial.html#creating_new_databases
