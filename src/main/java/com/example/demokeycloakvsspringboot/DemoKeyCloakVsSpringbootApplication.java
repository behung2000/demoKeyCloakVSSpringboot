package com.example.demokeycloakvsspringboot;

import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
}
