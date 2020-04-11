package com.github.gustavovitor.erriaga.api.service.person;

import com.github.gustavovitor.erriaga.api.domain.person.Person;
import com.github.gustavovitor.erriaga.api.repository.person.PersonRepository;
import com.github.gustavovitor.erriaga.api.repository.person.spec.PersonSpecification;
import com.github.gustavovitor.erriaga.api.repository.person.spec.filter.PersonFilter;
import com.github.gustavovitor.maker.service.ServiceMaker;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

import static java.util.Objects.isNull;

@Service
public class PersonService extends ServiceMaker<PersonRepository, Person, Long, PersonFilter, PersonSpecification> {

    @Override
    public void beforeDelete(Person object) {
        if (isNull(object)) {
            throw new EntityNotFoundException();
        }
    }
}
