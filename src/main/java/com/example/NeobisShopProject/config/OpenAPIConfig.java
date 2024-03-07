
package com.example.NeobisShopProject.config;

import java.util.List;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
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

@SecurityScheme(
        name = "bearerAuth",
        description = "JWT auth description",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)



@Configuration
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
                        .description("This API provides endpoints for store data.")
                        .license(mitLicense);

                return new OpenAPI().info(info).servers(List.of(devServer));
        }
}


