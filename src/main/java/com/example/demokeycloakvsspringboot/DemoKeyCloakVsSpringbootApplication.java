package com.example.demokeycloakvsspringboot;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Locale;

@SpringBootApplication
public class DemoKeyCloakVsSpringbootApplication {

    private static final Logger log = LoggerFactory
            .getLogger(DemoKeyCloakVsSpringbootApplication.class);


    @Bean
    public KeycloakSpringBootConfigResolver keycloakSpringBootConfigResolver(){
        return new KeycloakSpringBootConfigResolver();
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoKeyCloakVsSpringbootApplication.class, args);
    }

    //configuring default locale
    @Bean
    public LocaleResolver localeResolver()
    {
        AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
        localeResolver.setDefaultLocale(Locale.US);
        return localeResolver;
    }

    //configuring ResourceBundle
    @Bean
    public ResourceBundleMessageSource messageSource()
    {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("MyMessage");
        return messageSource;
    }


    //configuring swagger
    @Bean
    public OpenAPI customOpenAPI(@Value("1.5.8") String appVersion) {
        var securitySchemeName = "bearer-key";
        return new OpenAPI()
                .components(new Components().addSecuritySchemes(securitySchemeName,
                        new SecurityScheme()
                                .name(securitySchemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")))
                .info(new Info().title("Your API").version(appVersion)
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}
