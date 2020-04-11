package com.github.gustavovitor.erriaga;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static org.hamcrest.Matchers.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonOthersPathIT extends DefaultPersonTest {

    @Test
    public void should_whenFindByIdWithWrongId() {
        getRestAssuredHeader()
                .when()
                    .get("/999")
                .then()
                    .body(Matchers.is(""))
                    .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void shouldBeStatus400_whenPutForFindAllWithoutFilter() {
        getRestAssuredHeader()
            .when()
                .put("/search")
            .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void shouldReturn2PersonsAndStatus200_whenPutForFindAll() {
        getRestAssuredHeader()
            .when()
                .body("{ \"cpf\": \"000\" }")
                .put("/search")
            .then()
                .body("", Matchers.hasSize(0))
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void shouldReturn1PersonAndOnePage_whenUsePageableParamsWithFilter() {
        getRestAssuredHeader()
            .when()
                .body("{ \"pageable\": { \"size\": 5, \"page\": 0 }, \"object\": { \"cpf\": \"66764062099\" } }")
                .put("/search/page")
            .then()
                .statusCode(HttpStatus.OK.value())
                .body("content.size()", is(1))
                .body("totalPages", is(1))
                .body("totalElements", is(1));
    }

    @Test
    public void shouldReturn400_whenUsePageableParamsWithoutFilter() {
        getRestAssuredHeader()
            .when()
                .put("/search/page")
            .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void shouldBadRequest_whenSendInvalidPerson() {
        getRestAssuredHeader()
            .when()
                .body("{ }")
                .post()
            .then()
                .body("errorCount", is(3))
                .body("errors.size()", is(3))
                .body("errors.message",
                        Matchers.hasItems("O CPF é obrigatório.", "O nome é obrigatório.", "A data de nascimento é obrigatória."))
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void shouldReturn409Conflict_whenSendTwoPersonsWithSameCpf() {
        getRestAssuredHeader()
            .when()
                .body("{ \"name\": \"Jack Nilson\", \"gender\": \"MALE\", \"birthDate\": \"1955-03-21\", \"cpf\": \"04544048087\" }")
                .post();

        getRestAssuredHeader()
            .when()
                .body("{ \"name\": \"Jack Bauer\", \"gender\": \"MALE\", \"birthDate\": \"1930-03-21\", \"cpf\": \"04544048087\" }")
                .post()
            .then()
                .statusCode(HttpStatus.CONFLICT.value());
    }

    @Test
    public void shouldReturn409Conflict_whenPatchPersonWithDuplicatedCpf() {
        getRestAssuredHeader()
            .when()
                .body("{ \"cpf\": \"89633023009\", \"name\": \"Jack Nilson\", \"birthDate\": \"2018-10-10\" }")
                .patch("/".concat(getPersonInstance().getId().toString()))
            .then()
                .statusCode(HttpStatus.CONFLICT.value());
    }

    @Test
    public void shouldReturn400BadRequest_whenDeleteOnePersonWithInvalidId() {
        getRestAssuredHeader()
                .when()
                    .delete("/999")
                .then()
                    .statusCode(HttpStatus.BAD_REQUEST.value());
    }
}
