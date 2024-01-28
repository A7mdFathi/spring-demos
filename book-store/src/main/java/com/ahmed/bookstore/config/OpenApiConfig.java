package com.ahmed.bookstore.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
    info = @Info(
        contact = @Contact(
            name = "Ahmed fathi",
            email = "a.fathi@example.com",
            url = "https://aliboucoding.com"
        ),
        description = "Open API docs for spring project",
        title = "OpenApi demo project",
        version = "1.0"
    ),
    servers = {
        @Server(
            description = "LOCAL dev",
            url = "http://localhost:8080/"
        ),
        @Server(
            description = "PROD dev",
            url = "https://aliboucoding.com"
        )
    },
    security = {
        @SecurityRequirement(
            name = "bearerAuth"
        )
    }
    
)
@SecurityScheme(
    name = "bearerAuth",
    description = "Jwt description",
    scheme = "bearer",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
    
}
