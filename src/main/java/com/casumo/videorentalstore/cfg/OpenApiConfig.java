package com.casumo.videorentalstore.cfg;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class OpenApiConfig {

    @Bean
    public GroupedOpenApi api() {
        return GroupedOpenApi.builder()
                .group("Video Rental Store")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI().info(new Info().title("VideoRentalStore - REST API")
                .description("Video Rental Store")
                .version("1.0.0")
                .termsOfService("Casumo")
                .license(new License().name("Casumo Standard License")
                        .url("https://www.casumocareers.com/")));
    }

}
