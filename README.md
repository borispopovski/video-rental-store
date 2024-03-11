# Video Rental Store

### Video Rental Store API is java service as a Spring boot app which takes JSON string message with objects.

For further reference, please consider the following sections:

There are 4 endpoints (GET, POST):
* /film/add	(add films)
* /film/rent (rent films and calculate price)
* /film/get (get films)
* /film/return/{id} (return films and calculate penalty price)

There are Dto classes for receiving json message and easier implementation.
Objects fill directly when it will receive json message.
Objects stored in mariadb.
There is a configuration for mariadb in application.properties.

I use JPA, actually Hibernate for communication with a database.

My focus was a simple functional api with json messages (Dto), exception handling, store data in a database.
I could use "MapStruct" for (dto -> entity) and Redis or Infinispan for store data in memory
I forgot to write tests.

Access to API through a Swagger

* Swagger url: http://localhost:8080/swagger-ui/index.html
* I decided to use Spring Data JPA because it was a better option for me (one dependency and a little configuration)
