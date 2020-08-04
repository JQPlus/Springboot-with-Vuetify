package com.explore.galaxy.basic.configuration.origin;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 跨域配置
 */
@Configuration
public class OriginConfiguration {

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowCredentials(true)
//                        .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                        .allowedMethods("POST", "GET", "PUT", "DELETE")
//                        .allowedMethods("*")
                        .allowedOrigins("*")
                        .allowedHeaders("*");
            }
        };
    }
}

