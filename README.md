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

### Database configuration
The application tries to connect by default to a MySql database on `localhost:3306` and looks for the schema `form3`
The JDBC connection string is defined in `application.properties`, together with username and password, hence if you need different credentials you can change them in the file. 
However this configuration can be overridden at runtime too, by passing the following command line arguments:
```
spring.datasource.url = jdbc:mysql://<HOST>:<PORT>/<SCHEMA_NAME>
spring.datasource.username = <USERNAME>
spring.datasource.password = <PASSWORD>
```

For example:

```bash
java -jar payment-api-0.0.1-SNAPSHOT.jar --spring.datasource.url=jdbc:mysql://localhost:3306/form3?useSSL=false --spring.datasource.username=root --spring.datasource.password=passw0rd
```