package com.example.backend.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@OpenAPIDefinition(
        info = @Info(
                title = "청각장애인을 위한 영어 발음 교정 서비스 API 명세서",
                description = "청각장애인을 위한 영어 발음 교정 서비스 API 명세서입니다.",
                version = "v1"
        )
)
public class SwaggerConfig {
    private static final String SECURITY_SCHEME_NAME = "Authorization";

    @Bean
    @Profile("!prod") // profile 적용(운영에서 비활성화)
    public OpenAPI openAPI() {
        // Security 설정 : 모든 API에 Bearer 토큰 적용
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(SECURITY_SCHEME_NAME);
        Components components = new Components()
                .addSecuritySchemes(SECURITY_SCHEME_NAME,
                        new SecurityScheme()
                                .name(SECURITY_SCHEME_NAME)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                );

        return new OpenAPI()
                .addSecurityItem(securityRequirement)
                .components(components);
    }
}
