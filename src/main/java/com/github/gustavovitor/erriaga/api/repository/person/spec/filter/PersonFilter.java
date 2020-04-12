package com.github.gustavovitor.erriaga.api.repository.person.spec.filter;

import com.github.gustavovitor.erriaga.api.domain.person.Person;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class PersonFilter extends Person {

    private LocalDate birthDateStart;
    private LocalDate birthDateEnd;

    private LocalDateTime registerDateStart;
    private LocalDateTime registerDateEnd;

    private LocalDateTime modifiedDateStart;
    private LocalDateTime modifiedDateEnd;

}
