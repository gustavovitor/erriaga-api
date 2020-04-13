package com.github.gustavovitor.erriaga;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;


import static org.hamcrest.Matchers.is;

@TestPropertySource("/application-test.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonHappyPathIT extends DefaultPersonTest {

    @Test
    public void shouldReturn200AndOnePerson_whenFindById() {
        getRestAssuredHeader()
                .when()
                    .get("/".concat(getPersonInstance().getId().toString()))
                .then()
                    .body("", Matchers.notNullValue())
                    .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void shouldReturn2PersonsAndStatus200_whenPutForFindAll() {
        getRestAssuredHeader()
            .when()
                .body("{}")
                .put("/search")
            .then()
                .statusCode(HttpStatus.OK.value())
                .body("", Matchers.hasSize(2));
    }

    @Test
    public void shouldReturn1PersonPerPage_whenUsePageableParams() {
        getRestAssuredHeader()
            .when()
                .body("{ \"pageable\": { \"size\": 1, \"page\": 0 }, \"object\": {} }")
                .put("/search/page")
            .then()
                .statusCode(HttpStatus.OK.value())
                .body("content.size()", is(1));

        getRestAssuredHeader()
            .when()
                .body("{ \"pageable\": { \"size\": 1, \"page\": 1 }, \"object\": {} }")
                .put("/search/page")
            .then()
                .statusCode(HttpStatus.OK.value())
                .body("content.size()", is(1));
    }

    @Test
    public void shouldReturn201Created_whenSendValidPerson() {
        getRestAssuredHeader()
            .when()
                .body("{ \"name\": \"Jack Bauer\", \"gender\": \"MALE\", \"birthDate\": \"1955-03-21\", \"cpf\": \"04544048087\" }")
                .post()
            .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void shouldReturn200Ok_whenUpdateAValidPerson() {
        getRestAssuredHeader()
            .when()
                .body("{ \"id\": ".concat(getPersonInstance().getId().toString()).concat(", \"name\": \"Jack Bauer\", \"gender\": \"MALE\", \"birthDate\": \"1985-03-21\", \"cpf\": \"84754678060\" }"))
                .put("/".concat(getPersonInstance().getId().toString()))
            .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void shouldReturn200Ok_whenPatchAValidPerson() {
        getRestAssuredHeader()
            .when()
                .body("{ \"name\": \"Jack Nilson\", \"gender\": \"MALE\", \"birthDate\": \"1965-03-21\", \"cpf\": \"14216516025\" }")
                .patch("/".concat(getPersonInstance().getId().toString()))
            .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void shouldReturnNoContent_whenDeleteAPerson() {
        getRestAssuredHeader()
                .when()
                    .delete("/".concat(getPersonInstance().getId().toString()))
                .then()
                    .statusCode(HttpStatus.NO_CONTENT.value());
    }


}
