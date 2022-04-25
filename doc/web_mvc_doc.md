# MVC

## Backend 

[Sanzhar](https://github.com/zsanzharko) used the Java language on the server side and the Spring framework. In this part, I will write about the implementation of this project, so to speak, an overview of the project

* To work with a web application, we chose the **_Spring Web_** library, which can receive requests, and also send requests along with the Front part.
* For authorization and access for each type of user, **_Spring Security_** was used.
* To work with a MySQL database, **_Spring Data_** was chosen

### Overview

Software development architecture. In this case, we used a standard architecture that separates entities in such a way that we can clearly see the layers of separation of functionality.

We can see, in source path <br>
`src/main/java/kz/sdu/activitymonitoringsdu/`
* configuration
* controller
* dao
* dto
* entity
* enums
* handlers
* repository
* service

## About 
### Configuration

This folder has to configurate Spring project. We can see, in this folder have 3 config files. Simplify one file with security, secure all requesst to showing, but `src/main/resources/static` we do not secure, and basic welcome html file. User should to authorize, then security lead him to the door of the rest of the pages

```
.authorizeRequests()
                    .antMatchers("/home", "/**/*.js", "/**/*.css", "/images/*").permitAll()
                    .anyRequest()
                    .authenticated()
```

### Entity

This is entity who comes from database. This is something like model in MVC, but with full information about entity.
In folder, we can see all about User, projects, activities and other entities.

