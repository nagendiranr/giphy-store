# giphy-store

Giphy Store is an online web application that allows an user to search and save animated GIFs to a user profile using the GIPHY API.

## Tools & Technologies

* [Spring Web MVC](https://docs.spring.io/spring/docs/3.2.x/spring-framework-reference/html/mvc.html)
* [Spring Security](https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/reference/htmlsingle/#boot-features-security)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/reference/htmlsingle/#boot-features-jpa-and-spring-data)
* [Lombok](https://projectlombok.org/)
* [Bootstrap 3 Tutorial](https://www.w3schools.com/bootstrap/default.asp)
* [MySQL Installation Guide](https://dev.mysql.com/doc/mysql-installation-excerpt/5.7/en/)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/gradle-plugin/reference/html/)
* [Eclipse Buildship Gradle Integration](https://marketplace.eclipse.org/content/buildship-gradle-integration#group-details)

## Database Design

Auto generated hibernate queries by Spring JPA based on multiple `Entity` and their relationships required for the website.

* [SQL Scripts](https://github.com/nagendiranr/giphy-store/blob/master/src/main/resources/scripts/giphystore_db_scripts.sql)

## Getting Started
To install Giphy Store application, run the following commands:

```bash
git clone https://github.com/nagendiranr/giphy-store.git
cd giphy-store
```

### Java & Gradle
Run the following commands to ensure that you have valid versions of Java and Gradle installed:

```bash
$ java -version
java version "9.0.4"
Java(TM) SE Runtime Environment (build 9.0.4+11)
Java HotSpot(TM) 64-Bit Server VM (build 9.0.4+11, mixed mode)
```

```bash
$ ./gradlew -v

------------------------------------------------------------
Gradle 6.3
------------------------------------------------------------

Build time:   2020-03-24 19:52:07 UTC
Revision:     bacd40b727b0130eeac8855ae3f9fd9a0b207c60

Kotlin:       1.3.70
Groovy:       2.5.10
Ant:          Apache Ant(TM) version 1.10.7 compiled on September 1 2019
JVM:          1.8.0_251 (Oracle Corporation 25.251-b08)
OS:           Windows 10 10.0 x86
```

### MySQL

Create a new database or schema in MySQL instance.

Configure:
```application.properties
#Enable below line for JPA to create tables for the first time
#spring.jpa.hibernate.ddl-auto=create 
#Update database host/schema name below.
spring.datasource.url=jdbc:mysql://${mysql:localhost}:3306/<schema_name> 
spring.datasource.username=<username goes here>
spring.datasource.password=<password goes here>
```
### Building & Running Locally

To build the application:
```bash
./gradlew clean build
```

To start the application:
```bash
./gradlew bootRun
```

## Website Guide

#### Create an account: 

Simply create an account with username and password by clicking "Create an account" on `http://localhost:8080`.

#### Login:

Login to the website using username and password.

#### Create category:

Navigate to "My Categories" page for creating list of categories before you start searching Giphy.

#### Search giphy:

Navigate to "Home" page for searching your favorites Giphy and view the what's trending. The website is limited to show only 10 giphys at this moment.

#### Save giphy to category:

Click "Save" on your favorites giphy to store one of the categories created in Step 3.

#### View saved giphy:

Navigate to "My Favorites" for viewing saved giphy on selected category.

#### Remove giphy:

Click "Remove" on giphy to remove giphy from your favorites.
