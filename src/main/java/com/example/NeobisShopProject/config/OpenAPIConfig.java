package com.example.NeobisShopProject.config;

import java.util.List;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@SecurityScheme(type = SecuritySchemeType.HTTP, name = "bearerAuth", scheme = "bearer")

public class OpenAPIConfig {

    @Value("${DonutShop.openapi.dev-url}")
    private String shopUrl;


    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(shopUrl);
        devServer.setDescription("Server URL in Development environment");


        Contact contact = new Contact();
        contact.setEmail("gulnuremilbekova2@gmail.com");
        contact.setName("Gulnur");
        contact.setUrl("https://github.com/Noorahg");

        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("Spring Boot Shop API")
                .version("1.0")
                .contact(contact)
                .description("\"This API provides endpoints for store data.")
                .license(mitLicense);

        return new OpenAPI().info(info).servers(List.of(devServer));
    }
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.NeobisShopProject.config"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(List.of(apiToken()));
    }

    private ApiKey apiToken() {
        return new ApiKey("apiToken", "x-api-token", "header");
    }
}