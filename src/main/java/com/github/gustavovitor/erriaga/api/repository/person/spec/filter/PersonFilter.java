package com.github.gustavovitor.erriaga.api.repository.person.spec.filter;

import com.github.gustavovitor.erriaga.api.domain.person.Person;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PersonFilter extends Person {

    private LocalDate birthDateStart;
    private LocalDate birthDateEnd;

}
