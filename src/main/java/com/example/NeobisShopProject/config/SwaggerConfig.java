package com.example.NeobisShopProject.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.info.Contact;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${DonutShop.openapi.dev-url}")
    private String shopUrl;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.NeobisShopProject.controller"))
                .build()
                .apiInfo(apiInfo())
                .securitySchemes(List.of(apiToken()))
                .securityContexts(List.of(securityContext()));
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact();
        contact.setEmail("gulnuremilbekova2@gmail.com");
        contact.setName("Gulnur");
        contact.setUrl("https://github.com/Noorahg");

        return new ApiInfoBuilder()
                .title("Spring Boot Shop API")
                .version("1.0")
                .description("This API provides endpoints for store data.")
                .license("MIT License")
                .licenseUrl("https://choosealicense.com/licenses/mit/")
                .build();
    }

    private ApiKey apiToken() {
        return new ApiKey("apiToken", "x-api-token", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.any())
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return List.of(new SecurityReference("apiToken", authorizationScopes));
    }
}
