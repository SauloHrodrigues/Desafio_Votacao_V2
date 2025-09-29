package com.desafio.votacao.configuracoes;

import java.util.List;
import org.springframework.context.annotation.Bean;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiSwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API - Votação")
                        .version("v1")
                        .description(
                                "O presente projeto foi desencolvido em forma de desafio, com o objetico de avaliar " +
                                        "nossas habilidades em programação orientada a objetos, utilizando classes, " +
                                        "interfaces, heranças, polimorfismo e etc. Para isso, foi desenvolvido uma " +
                                        "aplicação, apenas backend, em Java. " +
                                        "A aplicação esta em forma de CRUD."
                        )
                        .contact(new Contact()
                                .name("Saulo Henrique Rodrigues")
                                .email("saulo.rodrigues@db.tec.br"))
                )
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("Local")
                ));
    }
}
