package br.com.fiap.postech.gestao_restaurantes.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI gestaoRestaurantes() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Gestão de Restaurantes API")
                                .version("v1")
                                .description("Projeto feito para criar API de Gestão de Restaurantes - Tech Challenge FIAP - Grupo 7")
                                .license(new License().name("Apache 2.0").url("https://github.com/FIAP-7/TECH-CHALLENGE"))
                );
    }
}
