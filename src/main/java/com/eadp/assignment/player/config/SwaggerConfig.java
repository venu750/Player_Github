package com.eadp.assignment.player.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket playerApi()
    {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.eadp.assignment.player"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo()) ;

    }
    private ApiInfo apiInfo(){
        return new ApiInfo(
                "REST API Player Service",
                "API for performing CRUD operation on Player",
                "2.0",
                "urn:tos",
                new Contact("Venu Gopal Panchumarthi", "", "vpanchumarthi@ea.com"),
                "License of API", "API license URL", Collections.emptyList());
    }
}
