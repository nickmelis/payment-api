# Payment API

This is a Java application based on the Spring Boot framework. It exposes a RESTful API that allows basic CRUD operations over payments.
It has support for MySql database, although it can support any database for which a JDBC driver is available.

### Build
The application uses Gradle as dependency manager. Gradle version 5 or above is recommended, as the application has not been tested with previous versions.
In order to build an executable jar it's enough to do 
```bash
gradle build
``` 
in the console, in the root project folder. It will generate the main executable jar, together with the source and javadoc ones, under build/libs.

### Run
Once the application JAR has been built, it can be run by doing 
```bash
java -jar payment-api-0.0.1-SNAPSHOT.jar
```
in the console, in the folder where the jar is.