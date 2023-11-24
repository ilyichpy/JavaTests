# JavaTests

In this project i learn how to make test with JUnit.

## Stack

1. Assembly system:

    `Maven`

2. Libraries for test:

    `Junit jupiter(5)`: Make parametrized test from different value source(csv file, usual values) 

    `spring-jdbc` and `hsqldb`: For fast access to data without raise DB.

    `Mockito`: tested the code without interface

## Installation

I make scope `test` in pom.xml and to run the code you need to run this in Test dir:

```
mvn test
```