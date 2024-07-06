package dev.hakeem.parkingapi_springboot3_practical.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SpringDocOpenApiConfig {
    public OpenAPI OpenAPI(){
        return new OpenAPI()
                           .info(
                            new Info()
                            .title("REST API - Spring Park")
                            .description("API para gestao de estacionamento de veiculos")
                            .version("v1")
                            .license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0.html"))
                            .contact(new Contact().name("dady hakeem").email("contatokenzala@gmail.com"))
                           );
    }
}
