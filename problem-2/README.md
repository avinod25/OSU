<!-- TABLE OF CONTENTS -->
## Table of Contents

* [About the Project](#about-the-project)
  * [Built With](#built-with)
* [Getting Started](#getting-started)

<!-- ABOUT THE PROJECT -->
## About The Project

 * [Problem 2]
 There are many different species of lobster, but they fall into two main kinds: Clawed and Spiny
 
```Data of lobsters
Clawed : Reef Lobster, Squat Lobster, American Lobster, European Lobster
Spiny : Rock Lobster, Slipper Lobster, Furry Lobster, 
```

Develop a Lobster RESTful Web Services to manage the different species of lobsters. The RESTful APIs should include to Get list of lobsters, Get a lobster’s details, create a lobster, update a lobster, and delete a lobster. 
Additionally, only the user who has an access token can create, update, or delete the species of lobster.

### Built With

* [Java 8](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)
* [Spring-boot](https://spring.io/projects/spring-boot)
* [HSQL DB](http://hsqldb.org/)
* [Hibernate]


<!-- GETTING STARTED -->
## Getting Started

To get a local copy of the project up and running, follow these simple steps:

1. Clone the repo
2. Import problem-2 project into eclipse workspace

3. Build project with Maven

```sh
mvn clean package
```

4. Run as Spring boot app or Java application by selecting com.osu.demo.Problem2Application class

```sh
java com.osu.demo.Problem2Application
```
5. Check the stdout to make sure no exceptions are thrown
Once the application runs you should see something like this

```sh
INFO 16536 --- [           main] com.osu.demo.Problem2Application         : Started Problem2Application in 2.992 seconds (JVM running for 3.449)
```
6. You can access API documentation using below URL

```sh
http://localhost:8080/demo/swagger-ui.html
```

7. You can also test API using above swagger documentation

