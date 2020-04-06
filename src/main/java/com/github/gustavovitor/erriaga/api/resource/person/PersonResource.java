package com.github.gustavovitor.erriaga.api.resource.person;

import com.github.gustavovitor.erriaga.api.domain.person.Person;
import com.github.gustavovitor.erriaga.api.repository.person.spec.filter.PersonFilter;
import com.github.gustavovitor.erriaga.api.service.person.PersonService;
import com.github.gustavovitor.maker.resource.ResourceMaker;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PersonResource extends ResourceMaker<PersonService, Person, Long, PersonFilter> {}
