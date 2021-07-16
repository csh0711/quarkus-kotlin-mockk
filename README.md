![Java CI with Gradle](https://github.com/csh0711/quarkus-kotlin-mockk/workflows/Java%20CI%20with%20Gradle/badge.svg) 
[![Pure Kotlin](https://img.shields.io/badge/100%25-kotlin-blue.svg)](https://kotlinlang.org/)

# Quarkus Kotlin app with tests using Junit 5 and MockK

Sample of a simple [Quarkus](https://quarkus.io/) application written in [Kotlin](https://kotlinlang.org/). 
Shows how [Junit 5](https://junit.org/junit5/) and [MockK](https://mockk.io/) may be used for mocking CDI beans.

**Note:** A detailed blog post where the motivation and whole set up is described can be found here: 
https://www.novatec-gmbh.de/blog/testing-quarkus-with-kotlin-junit-and-mockk

+ [Scenario](#scenario)
+ [Running and packaging the application](#running-and-packaging-the-application)
+ [Executing the tests](#executing-the-tests)
    
## Scenario
<img src="quarkus-kotlin-mockk.png" alt="Scenario" width="700"/>

## Running and packaging the application

### Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./gradlew quarkusDev
```

### Packaging and running the application

The application can be packaged using:
```shell script
./gradlew build
```
It produces the `quarkus-kotlin-mockk-1.0.0-SNAPSHOT-runner.jar` file in the `/build` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `build/lib` directory.

If you want to build an _über-jar_, execute the following command:
```shell script
./gradlew build -Dquarkus.package.type=uber-jar
```

The application is now runnable using `java -jar build/quarkus-kotlin-mockk-1.0.0-SNAPSHOT-runner.jar`.

## Executing the tests
The tests can be either executed by running the Gradle command
```
./gradlew clean test
```
Or directly as usual in the IDE, e.g. IntelliJ. 
E.g. `Run` > `RestControllerTestsWithBeforeEach.kt` etc.

