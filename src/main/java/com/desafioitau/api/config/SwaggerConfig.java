package com.desafioitau.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi apiPublicas() {
        return GroupedOpenApi.builder()
                .group("apis-publicas")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Transações")
                        .description("Desafio de Programação do Itaú Unibanco")
                        .contact(new Contact()
                                .name("Everton Rocha")
                                .email("imevertonrch@gmail.com")
                                .url("https://github.com/seu-repositorio"))
                        .version("1.0.0"));
    }
}
