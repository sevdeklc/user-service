package com.user.service.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(

        ),
        servers = {
                @Server(
                        description = "Local Environment",
                        url = "${local.server.url}")
        }
)
@Configuration
public class OpenApiConfig { }
