![Java CI with Gradle](https://github.com/csh0711/quarkus-kotlin-mockk/workflows/Java%20CI%20with%20Gradle/badge.svg) 
[![Pure Kotlin](https://img.shields.io/badge/100%25-kotlin-blue.svg)](https://kotlinlang.org/)

# Quarkus Kotlin app with tests using Junit 5 and MockK

Sample of a simple [Quarkus](https://quarkus.io/) application written in [Kotlin](https://kotlinlang.org/). 
Shows how [Junit 5](https://junit.org/junit5/) and [MockK](https://mockk.io/) may be used for mocking CDI beans.

## Scenario
<img src="quarkus-kotlin-mockk.png" alt="Scenario" width="600"/>

## Running and packaging the application

### Running the application in your IDE (e.g. IntelliJ)
You can run your application by starting the `MyApplication.kt`.

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

