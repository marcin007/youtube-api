# Youtube-api
This is clone of youtube api. [Spring Boot] [H2] [Maven] [AsserJ] [Mockito]

## About

This is a simple clone api of youtube.com with 
**[Spring Boot](https://spring.io/projects/spring-boot)**
This application contains only api and tests. All endpoints are working.


## Requirements
This demo is build with with Maven 3.6.x and Java 11

## Usage
Just start the application with the Spring Boot maven plugin (`mvn spring-boot:run`).

You can use the **H2-Console** for exploring the database under [http://localhost:8080/h2console](http://localhost:8080/h2console)


## Backend


Endpoints for Category Controller:
```
GET:
/categories - in response you will get list of categories.

POST: 
/categories - by this endpoint you can add new category.

PATCH:
/categories/{id} - by this endpoint you can update specific category.

DELETE:
/categories/{id} - by this endpoint you can delete specific category.
```


Endpoints for Channel Controller:
```
GET:
/channels - in response you will get list of channels.
/channels/{id} - in response you will get specific channel by id.
/users/{id}/channels - in response you will get specific channel by user id.

PATCH:
/channels/{id} - by this endpoint you can update specific channel.

DELETE:
/channels/{id} - by this endpoint you can delete specific channel.
```


Endpoints for Comment Controller:
```
GET:
/comments - in response you will get list of comments.
/films/{id}/comments - in response you will get list of comments by film id.

POST:
/comments - by this endpoint you can add new comment.

PATCH:
/comments/{id} - by this endpoint you can update specific comment.

DELETE:
/comments/{id} - by this endpoint you can delete specific comment.
```


Endpoints for Film Controller:
```
GET:
/films - in response you will get list of films.
/film/{id} - in response you will get specific film by id.
/channels/{id}/films - in response you will get list of films by channel id.
```


Endpoints for Reply Controller:
```
GET:
/replies - in response you will get list of replies.
/comments/{id}/replies - in response you will get list of replies by comment id.

POST:
/replies - by this endpoint you can add new reply.
```


Endpoints for User Controller:
```
GET:
/users - in response you will get list of users.
/users/{user_id} - in response you will get specific user by id.

POST:
/users - by this endpoint you can add new user.

PATCH:
/users/{id} - by this endpoint you can update specific user.

DELETE:
/users/{id} - by this endpoint you can delete specific user.
```


Endpoints for User Watched Film Controller:
```
GET:
/watchedHistory - in response you will get list of watched films.
/users/{id}/watchedHistory - in response you will get list of watched films by user id.

POST:
/watchedHistory - by this endpoint you can add new watched film.

DELETE:
/users/{id}/watchedHistory - by this endpoint you can delete all watched films from history.
```

## Copyright and license

The code is released under the [MIT license](LICENSE?raw=true).
