package com.example.mentorsstudents.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi userServiceApi() {
        return GroupedOpenApi.builder()
                .group("mentor-student")
                .pathsToMatch("/auth/**", "/register/**", "/user/**", "/mentors/**", "/students/**", "/users/settings/**")
                .build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("mentors-students API")
                                .version("1.0.0")
                                .contact(
                                        new Contact()
                                                .email("maksim.ananev.1994@mail.ru")
                                                .url("https://localhost:8080/")
                                                .name("Ananev Maksim")
                                )
                );
    }
}
