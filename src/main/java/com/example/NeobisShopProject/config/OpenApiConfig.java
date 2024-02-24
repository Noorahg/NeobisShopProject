
package com.example.NeobisShopProject.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
        import io.swagger.v3.oas.annotations.info.Contact;
        import io.swagger.v3.oas.annotations.info.Info;
        import io.swagger.v3.oas.annotations.security.SecurityScheme;
        import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.beans.factory.annotation.Value;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Gulnur",
                        email = "gulnuremilbekova2@gmail.com"
                ),
                title = "Donuts - online  store",
                description = "OpenApi documentation for  Spring Project",
                version = "0.0.1"
        ),
        servers = {
                @Server(
                        description = "Local environment",
                        url = "http://localhost:8080"
                )
        }
)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT auth description",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)

public class OpenApiConfig {
        @Value("${DonutShop.openapi.dev-url}")
        private String shopUrl;


}
