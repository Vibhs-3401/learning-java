package com.register.learning.config;

import com.register.learning.constants.SecurityConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .securitySchemes(Collections.singletonList(apiKey()))
                .securityContexts(Arrays.asList(jwtSecurityContext()));
    }

    private ApiInfo apiInfo() {
        return new ApiInfo("Learning jwt",
                "Learning jwt and implemented swagger for the second time",
                "API V1",
                "",
                new Contact("User", "#", ""),
                "Licence of API",
                "localhost:8081/api", Collections.emptyList());
    }

    private ApiKey apiKey() {return new ApiKey("JWT", SecurityConstants.JWT_AUTHORIZATION_TOKEN, "header");}

    private SecurityContext jwtSecurityContext() {
        return SecurityContext.builder()
                .securityReferences(jwtAuth())
                .operationSelector(operationContext -> !operationContext.requestMappingPattern().matches("/login"))
                .build();
    }

    List<SecurityReference> jwtAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope(
                "global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
    }
}
