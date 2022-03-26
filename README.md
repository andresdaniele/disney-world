# Disney World
## Alkemy pre-aceleracion challenge. Spring Boot API 
    
>This API was developed using REST pattern. . You can explore the Disney World through its movies, series, characters and genres.


### You need:

- Java 11.

- Maven 3.

- MySQL 8.

***

### Setup

1. Clone this repository.
2. Create a new DataBase called "disney-world" using MySQL Workbench o DBeaver.
3. Check MySQL server password and username in application.properties file:
    + `spring.datasource.username=root` 
    + `spring.datasource.password=root` 
4. Create an account on SendGrid, get your API Key and setup enviroment variables in your IDE.
5. Change your SendGrid registered email in application.properties file:
    `alkemy.disneyWorld.email.sender=email@email.com `
6. Build and run the project on your IDE. Check if all entities have been added to the database.

#### Postman
You can find a Postman Collection in the project or https://documenter.getpostman.com/view/19909814/UVyn1Hxq


