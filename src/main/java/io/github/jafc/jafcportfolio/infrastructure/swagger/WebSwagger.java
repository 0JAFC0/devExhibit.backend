package io.github.jafc.jafcportfolio.infrastructure.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class WebSwagger {

    @Value("${swagger.api.version}")
    private String version;
    @Value("${swagger.title}")
    private String title;
    @Value("${swagger.description}")
    private String description;
    @Value("${swagger.contactName}")
    private String contactName;
    @Value("${swagger.contactUrl}")
    private String contactUrl;
    @Value("${swagger.contactEmail}")
    private String contactEmail;

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder().group("dev.exhibit").pathsToMatch("/**").build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(info())
                .components(new Components()
                        .addSecuritySchemes("bearer-jwt",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .in(SecurityScheme.In.HEADER)
                                        .name("Authorization")))
                .addSecurityItem(new SecurityRequirement().addList("bearer-jwt", Arrays.asList("read", "write")));
    }

    private Info info() {
        Contact contact = new Contact();
        contact.setName(contactName);
        contact.setEmail(contactEmail);
        contact.setUrl(contactUrl);

        return new Info()
                .title(title)
                .description(description)
                .version(version)
                .contact(contact);
    }
}
