package com.joan.florit.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
	return new OpenAPI()
		.info(new Info()
			.title("Prices API")
			.version("1.0")
			.description("Prices API with Swagger/OpenAPI integration")
			.contact(new Contact()
				.name("Joan Florit")
				.email("jfloritmir@gmail.com")
				.url("")));
    }
}