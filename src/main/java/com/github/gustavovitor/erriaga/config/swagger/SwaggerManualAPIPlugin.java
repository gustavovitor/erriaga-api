package com.github.gustavovitor.erriaga.config.swagger;

import com.fasterxml.classmate.TypeResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.OperationBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiDescription;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ApiListingScannerPlugin;
import springfox.documentation.spi.service.contexts.DocumentationContext;
import springfox.documentation.spring.web.readers.operation.CachingOperationNameGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;

@Component
public class SwaggerManualAPIPlugin implements ApiListingScannerPlugin {

    @Autowired
    private CachingOperationNameGenerator operationNameGenerator;

    @Override
    public List<ApiDescription> apply(DocumentationContext documentationContext) {
        return new ArrayList<ApiDescription>(
                Arrays.asList(
                        new ApiDescription(
                                "token-endpoint",
                                "/oauth/token",
                                "OAuth2 Spring Boot",
                                Arrays.asList(
                                        new OperationBuilder(operationNameGenerator)
                                                .codegenMethodNameStem("oauthtokenPOST")
                                                .authorizations(Collections.singletonList(
                                                        new SecurityReference("Basic",
                                                                new AuthorizationScope[0])))
                                                .method(HttpMethod.POST)
                                                .tags(newHashSet("token-endpoint"))
                                                .summary("OAuth2 Spring Boot")
                                                .notes("Utilize o email e senha caso tenha se cadastrado anteriormente. \n\n" +
                                                        "Copie o access_token para utilizar na outro grupo de API.")
                                                .parameters(Arrays.asList(
                                                        new ParameterBuilder()
                                                                .description("Grant Type (password)")
                                                                .type(new TypeResolver().resolve(String.class))
                                                                .name("grant_type")
                                                                .parameterType("query")
                                                                .parameterAccess("access")
                                                                .defaultValue("password")
                                                                .required(true)
                                                                .modelRef(new ModelRef("String"))
                                                                .build(),
                                                        new ParameterBuilder()
                                                                .description("Email")
                                                                .type(new TypeResolver().resolve(String.class))
                                                                .name("username")
                                                                .parameterType("query")
                                                                .parameterAccess("access")
                                                                .defaultValue("admin@admin.com")
                                                                .required(true)
                                                                .modelRef(new ModelRef("String"))
                                                                .build(),
                                                        new ParameterBuilder()
                                                                .description("Senha")
                                                                .type(new TypeResolver().resolve(String.class))
                                                                .name("password")
                                                                .parameterType("query")
                                                                .parameterAccess("access")
                                                                .defaultValue("1")
                                                                .required(true)
                                                                .modelRef(new ModelRef("String"))
                                                                .build()
                                                ))
                                                .responseMessages(responseMessages())
                                                .responseModel(new ModelRef("Map"))
                                                .build()
                                ),
                                false
                        )
                )
        );

    }

    private Set<ResponseMessage> responseMessages() {
        return newHashSet(new ResponseMessageBuilder()
                        .code(200)
                        .message("Token received")
                        .responseModel(new ModelRef("string"))
                        .build(),
                new ResponseMessageBuilder()
                        .code(401)
                        .message("Usuário ou senha inválida(os).")
                        .responseModel(new ModelRef("string"))
                        .build());
    }

    @Override
    public boolean supports(DocumentationType documentationType) {
        return DocumentationType.SWAGGER_2.equals(documentationType);
    }
}
