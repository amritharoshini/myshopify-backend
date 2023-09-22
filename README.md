# My Project

This is a personal project developed using spring boot microservice project for an ecommerce application name - MyShopify. 

# Architecture

- There is a microservices-parent which contains the common dependencies of all the microservices in the pom.xml
- API-GATEWAY service to route all the requests and implemented a custom filter that filters the requests based on criteria
- SERVICE-DISCOVERY service registers all the microservices
- USER-SERVICE is developed for user registeration and authentication through Spring Security and JWT Authentication which has connection to MySql Database of the Users in the application through Spring Data JPA.
- PRODUCT-SERVICE is developed for adding and retrieving products from MongoDB database specifing the title, category, price and image urls.
- Others services like ORDER-SERVICE, INVENTORY-SERVICE are part of the architecture with specific functionalities.

# Technologies

- Spring Cloud
- Spring Gateway
- Spring Security JWT for authentication
- RestFul Web Services and WebClient for Inter microservice communication.
- Spring Data JPA
- Spring Data MongoDb
- Maven



