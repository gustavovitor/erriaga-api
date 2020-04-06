package com.github.gustavovitor.erriaga.api.service.person;

import com.github.gustavovitor.erriaga.api.domain.person.Person;
import com.github.gustavovitor.erriaga.api.repository.person.PersonRepository;
import com.github.gustavovitor.erriaga.api.repository.person.spec.PersonSpecification;
import com.github.gustavovitor.erriaga.api.repository.person.spec.filter.PersonFilter;
import com.github.gustavovitor.maker.service.ServiceMaker;
import org.springframework.stereotype.Service;

@Service
public class PersonService extends ServiceMaker<PersonRepository, Person, Long, PersonFilter, PersonSpecification> {
}
