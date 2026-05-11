package com.tubetv.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    //Um método para pegar as informações e entregar para o swagger usar, com isso os dois endpoints criados no 'application' ja podem ser vistos
    @Bean
    public OpenAPI getOpenAPI() {

        Contact contact = new Contact();
        contact.name("Felipe");
        contact.email("gaalbiatii.felipe@gmail.com");

        // setando algumas informações inicias da aplicação
        Info info = new Info();
        info.title("TubeTv");
        info.version("1.0");
        info.description("Aplicação para gerenciamento de catálogo de filmes");
        info.contact(contact); // objeto contact gerado na linha 16


        return new OpenAPI().info(info);
    }
}
