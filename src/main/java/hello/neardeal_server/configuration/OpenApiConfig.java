package hello.neardeal_server.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("NearDeal API Document")
                .version("v0.0.1")
                .description("NearDeal API 명세서입니다.");
        return new OpenAPI()
                .components(new Components())
//                .addSecurityItem(new SecurityRequirement().addList("Authentication"))
//                .components(new Components()
//                        .addSecuritySchemes("Authorication",
//                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("Bearer").bearerFormat("JWT")
//                        ))
                .info(info);
    }
}
