package com.github.gustavovitor.erriaga.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.ServletContext;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api(ServletContext servletContext) {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("Erriagá")
                        .description("API da aplicação de RH.")
                        .version("1.0.0")
                        .build())
                .groupName("com.github.gustavovitor")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.github.gustavovitor.erriaga.api.resource"))
                .paths(PathSelectors.any())
                .build();
    }

}