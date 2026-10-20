package com.ufn.movieflix.config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI getOpenAPI() {

        Contact contato = new Contact();
        contato.name("Matheus");
        contato.email("matheuspichler94@gmail.com");

        Info info = new Info();
        info.title("MovieFlix");
        info.version("V1");
        info.description("Aplicação para gerenciamento de catálogos de filmes");
        info.contact(contato);

        //Esquema de autenticação JWT, habilita o botão "Authorize" no Swagger
        SecurityScheme esquemaJwt = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT");

        return new OpenAPI()
                .info(info)
                .components(new Components().addSecuritySchemes("bearerAuth", esquemaJwt))
                //Exige o token em todas as rotas, as rotas públicas sobrescrevem com @SecurityRequirements vazio
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"));
    }

}
