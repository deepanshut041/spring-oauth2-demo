package io.github.deepanshut041.sod.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ApiConfiguration {

    @Bean
    fun customOpenAPI(@Value("\${springdoc.version}") appVersion: String?): OpenAPI? {
        return OpenAPI()
                .components(Components().addSecuritySchemes("bearerAuth",
                        SecurityScheme().apply {
                            name = "bearerAuth"
                            type = SecurityScheme.Type.HTTP
                            bearerFormat = "JWT"
                            scheme = "bearer"
                        }))
                .info(Info().title("Spring Oauth2 Demo").version(appVersion)
                        .license(License().name("Apache 2.0").url("http://springdoc.org")))

    }
}