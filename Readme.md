                                        Meal Recommendation Microservice

This is a Spring Boot-based microservice that recommends the top 10 healthy meals from 
restaurants based on a given cuisine and city. The service integrates with the Documenu
and Edamam APIs to fetch live data, and uses an offline fallback mechanism to ensure seamless 
operation even when APIs are unavailable or the service is running in offline mode. It adheres
to best practices in software engineering, including SOLID principles, the 12-factor methodology,
and HATEOAS for hypermedia-driven responses.

Features::

* Provides meal recommendations based on city and cuisine.
* Offline mode support using fallback JSON data.
* fallback for API failures with offline backup data.
* RESTful API accessible via Swagger UI.
* Pagination support for large datasets.
* CI/CD-ready with Docker integration and pipeline scripts.
* Secure handling of API keys via application.properties.

Design Patterns Used::

* Service Layer Pattern: Encapsulates business logic in services (RecommendationService, DocumenuService, EdamamService).
* Factory Pattern: Creates objects for recipes and restaurant DTOs in services.
* Strategy Pattern: Switches between online and offline modes dynamically.
* Adapter Pattern: Integrates third-party APIs (Documenu and Edamam) and converts responses into internal models.
* Template Method Pattern: Provides a common flow for fetching data, applying offline fallback logic.


Getting Started::

* Clone the repository.
* Add your API keys for Documenu and Edamam in application.properties.
* Set offline.mode=true to use fallback mode.
* Run the application and access Swagger UI at http://localhost:8080/swagger-ui.html.


Tech Stack:

* Java 17
* Spring Boot 3.4
* Springdoc OpenAPI (Swagger UI)
* Docker
* REST APIs
