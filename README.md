# TTTN_MobileWeb

# Databse:

## Environments

MySQL version 10.4.19-MariaDB
Your favorite database administration tool (MySQL WorkBench, DBeaver...)


## How to run

Run (exicute) db_mobile_shop.sql file in your database adminstration tool


## Server

Server Host: localhost
Port: 3306
Database: db_mobile_shop


# Back End:

## Environments

Java AdoptOpenJDK 11.0.3
Tomcat 9.0.22
SpringBoot 2.2.1
Gradle 5.2.1
Oracle 18c Express Edition
Your favorite IDE (Eclipse, IntelliJ, Visual Studio Code...)


## Database connection

spring.datasource.driver-class-name= com.mysql.jdbc.Driver
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5InnoDBDialect
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=none
spring.datasource.url=jdbc:mysql://localhost:3306/db_mobile_shop?zeroDateTimeBehavior=convertToNull
spring.datasource.username=root
spring.datasource.password=123456


## How to build bpr API to WAR file

- Open the terminal or cmd
- Run there commands

gradle build -x test


## How to run API

Run src/main/java/com/tannd/commercemanager/CommerceManagerApplication

The bpr API will be ran at http://localhost:8080/api


# Front End:

## Environments: 

NodeJS version 14.17.1
Angular CLI version 8.0.3.
Your favorite IDE (Eclipse, IntelliJ, Visual Studio Code...)


## Development server

Run ng serve for a dev server. Navigate to http://localhost:4200/. The app will automatically reload if you change any of the source files.


## Code scaffolding

Run ng generate component component-name to generate a new component. You can also use ng generate directive|pipe|service|class|guard|interface|enum|module.


## Build

Run ng build to build the project. The build artifacts will be stored in the dist/ directory. Use the --prod flag for a production build.
