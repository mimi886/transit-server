package com.transit.rest.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration
public class WebConfiguration {
    
    @Bean
    public WebMvcConfigurer corsConfigurer(){
        log.info("================ initialize web configuration =====================");
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("POST","GET", "PUT", "DELETE");
            }
        };
    }
}
