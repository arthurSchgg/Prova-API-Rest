package br.com.avaliacao.api_telemetria.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Faz a configuração do Swagger, colocando informações de contato e nome da API
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customApi() {
        return new OpenAPI().info(new Info()
                .title("API Telemetria")
                .description("API Rest de telemetria de motores WEG")
                .contact(new Contact()
                        .name("Arthur Miguel Schlichting")
                        .email("arthums2904@gmail.com")));
    }
}
