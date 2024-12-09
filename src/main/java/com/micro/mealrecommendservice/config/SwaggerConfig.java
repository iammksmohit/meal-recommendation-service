package com.micro.mealrecommendservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Meal Recommendation API Service")
                        .version("1.0")
                        .description("API documentation for Meal Recommendation Microservice")
                        .contact(new Contact()
                                .name("Mohit")
                                .email("mohitchemistry73@gmail.com")));
    }


}