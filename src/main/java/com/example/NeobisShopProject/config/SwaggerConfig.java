package com.example.NeobisShopProject.config;

import java.util.List;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.info.Contact;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;



@Configuration
@EnableSwagger2
@SecurityScheme(type = SecuritySchemeType.HTTP, name = "bearerAuth", scheme = "bearer")
public class SwaggerConfig extends WebMvcConfigurationSupport {

    @Value("${DonutShop.openapi.dev-url}")
    private String shopUrl;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.NeobisShopProject.controller"))
                .paths(PathSelectors.ant("/product/**")) // Adjust the path as needed
                .build()
                .apiInfo(apiInfo())
                .securitySchemes(List.of(apiToken()));
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

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    private ApiKey apiToken() {
        return new ApiKey("apiToken", "x-api-token", "header");
    }
}
