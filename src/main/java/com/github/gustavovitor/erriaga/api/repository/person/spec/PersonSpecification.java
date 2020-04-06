package com.github.gustavovitor.erriaga.api.repository.person.spec;

import com.github.gustavovitor.erriaga.api.domain.person.Person;
import com.github.gustavovitor.erriaga.api.domain.person.Person_;
import com.github.gustavovitor.erriaga.api.repository.person.spec.filter.PersonFilter;
import com.github.gustavovitor.maker.repository.SpecificationBase;
import org.apache.commons.lang3.StringUtils;

import javax.management.ReflectionException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

public class PersonSpecification extends SpecificationBase<PersonFilter> {

    public PersonSpecification(PersonFilter object) throws ReflectionException {
        super(object);
    }

    @Override
    public Predicate toPredicate(Root<PersonFilter> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (nonNull(getObject().getId()))
            predicates.add(criteriaBuilder.equal(root.get(Person_.id), getObject().getId()));

        if (StringUtils.isNotBlank(getObject().getName()))
            predicates.add(criteriaBuilder.like(root.get(Person_.name), "%" + getObject().getName() + "%"));

        if (StringUtils.isNotBlank(getObject().getEmail()))
            predicates.add(criteriaBuilder.like(root.get(Person_.email), "%" + getObject().getEmail() + "%"));

        if (StringUtils.isNotBlank(getObject().getCpf()))
            predicates.add(criteriaBuilder.like(root.get(Person_.cpf), "%" + getObject().getCpf() + "%"));

        if (StringUtils.isNotBlank(getObject().getNationality()))
            predicates.add(criteriaBuilder.like(root.get(Person_.nationality), "%" + getObject().getNationality() + "%"));

        if (StringUtils.isNotBlank(getObject().getBirthPlace()))
            predicates.add(criteriaBuilder.like(root.get(Person_.birthPlace), "%" + getObject().getBirthPlace() + "%"));

        if (nonNull(getObject().getBirthDateStart()) && nonNull(getObject().getBirthDateEnd())) {
            predicates.add(criteriaBuilder.between(root.get(Person_.birthDate), getObject().getBirthDateStart(), getObject().getBirthDateEnd()));
        } else if (nonNull(getObject().getBirthDateStart())) {
            predicates.add(criteriaBuilder.between(root.get(Person_.birthDate), getObject().getBirthDateStart(), LocalDate.MAX));
        } else if (nonNull(getObject().getBirthDateEnd())) {
            predicates.add(criteriaBuilder.between(root.get(Person_.birthDate), LocalDate.MIN, getObject().getBirthDateEnd()));
        }

        return super.toPredicate(root, criteriaQuery, criteriaBuilder);
    }
}
