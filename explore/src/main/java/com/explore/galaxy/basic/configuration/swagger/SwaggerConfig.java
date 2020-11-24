package com.explore.galaxy.basic.configuration.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

@Configuration
@EnableSwagger2
@Profile({"dev","test"})
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.explore.galaxy.demo.*.controller"))
//                .apis(RequestHandlerSelectors.basePackage("com.explore.galaxy.platform.applications.login.controller"))
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any()).build()
                .securityContexts(Arrays.asList(securityContexts()))
                .securitySchemes(Arrays.asList(securitySchemes()));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API Doc")
                .description("Restful api document.")
                .version("1.0.0")
                .build();
    }

    /**
     * @description 配置名为Authorization的请求头
     */
    private SecurityScheme securitySchemes() {
        return new ApiKey("Authorization", "Authorization", "header");
    }

    /**
     * @description 需要携带Token的请求
     */
    private SecurityContext securityContexts() {
        return SecurityContext.builder()
                .forPaths(PathSelectors.any())
                .build();
    }
}
