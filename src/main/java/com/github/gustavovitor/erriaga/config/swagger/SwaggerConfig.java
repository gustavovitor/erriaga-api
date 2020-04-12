package com.github.gustavovitor.erriaga.config.swagger;

import com.github.gustavovitor.erriaga.config.domain.APIConfig;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.ServletContext;
import java.util.Arrays;
import java.util.Collections;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {

    @Autowired
    private APIConfig apiConfig;

    @Bean
    public Docket basicAuthSecuredApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("Erriagá")
                        .description("API da aplicação de RH. Aqui estão as rotas referentes à login e cadastro de usuários. Por favor utilizar" +
                                " autenticação com usuário \"erriaga-api\" e senha \"w+)Vjj!Y()Ed%j?bL;(%>.xa6&ZktM#/\" \n\n" +
                                "Para pegar o seu token e consumir o próximo grupo de API utilize a rota /oauth/token dentro do token-endpoint.")
                        .version("1.0.0")
                        .build())
                .groupName("Auth")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.github.gustavovitor.erriaga.api.resource"))
                .paths(Predicates.in(Arrays.asList("/public/user/register", "/oauth/token", "/public/sources")))
                .build()
                .securitySchemes(Collections.singletonList(new BasicAuth("Basic")))
                .securityContexts(Collections.singletonList(xBasicSecurityContext()));
    }

    private SecurityContext xBasicSecurityContext() {
        return SecurityContext.builder()
                .securityReferences(Collections.singletonList(
                        new SecurityReference("Basic",
                                new AuthorizationScope[0])))
                .build();
    }

    @Bean
    public Docket api(ServletContext servletContext) {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("Erriagá")
                        .description("API da aplicação de RH. Rotas seguras com autenticação OAuth2 utilizando Token JWT. " +
                                "Favor utilizar o token com prefixo Bearer access_token.")
                        .version("1.0.0")
                        .build())
                .groupName("Security")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.github.gustavovitor.erriaga.api.resource"))
                .paths(paths())
                .build()
                .securitySchemes(Collections.singletonList(new ApiKey("Authorization",
                        "Authorization",
                        "header")))
                .securityContexts(Collections.singletonList(xAuthTokenSecurityContext()));
    }

    private Predicate<String> paths() {
        return or(
                regex("/person.*"),
                regex("/token/revoke"),
                regex("/chat.*"));
    }

    private SecurityContext xAuthTokenSecurityContext() {
        return SecurityContext.builder()
                .securityReferences(Collections.singletonList(
                        new SecurityReference("Authorization",
                                new AuthorizationScope[0])))
                .build();
    }

}