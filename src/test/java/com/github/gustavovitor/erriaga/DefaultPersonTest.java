package com.github.gustavovitor.erriaga;

import com.github.gustavovitor.erriaga.api.domain.person.Person;
import com.github.gustavovitor.erriaga.api.repository.person.PersonRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static java.util.Objects.isNull;

@Getter
@SpringBootTest
public class DefaultPersonTest {

    @Autowired
    private PersonRepository personRepository;
    private String token;
    private Person personInstance;

    @LocalServerPort
    private Integer port;

    @BeforeEach
    public void loadToken() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/api/person";

        cleanup();
        defaultData();
        getAccessToken();
    }

    protected RequestSpecification getRestAssuredHeader() {
        return RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer ".concat(token));
    }

    public void getAccessToken() {
        if (isNull(this.token)) {
            String response = RestAssured.given()
                    .basePath("/api/oauth/token")
                    .contentType(ContentType.URLENC)
                    .header("Authorization", "Basic ZXJyaWFnYS1hcGk6dyspVmpqIVkoKUVkJWo/Ykw7KCU+LnhhNiZaa3RNIy8=")
                    .when()
                    .formParam("grant_type", "password")
                    .formParam("username", "admin@admin.com")
                    .formParam("password", "1")
                    .post().body().asString();

            JacksonJsonParser jsonParser = new JacksonJsonParser();
            this.token = jsonParser.parseMap(response).get("access_token").toString();
        }
    }

    public void cleanup() {
        personRepository.deleteAll();
    }

    public void defaultData() {
        Person person1 = new Person();
        person1.setBirthDate(LocalDate.now());
        person1.setCpf("66764062099");
        person1.setName("Nilson Tokio");

        personInstance = personRepository.save(person1);

        Person person2 = new Person();
        person2.setBirthDate(LocalDate.now());
        person2.setCpf("89633023009");
        person2.setName("Jack Tokio");

        personRepository.save(person2);
    }

}
