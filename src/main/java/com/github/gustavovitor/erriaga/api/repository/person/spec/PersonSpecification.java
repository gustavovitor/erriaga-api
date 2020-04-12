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
import javax.persistence.metamodel.SingularAttribute;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
            predicates.add(criteriaBuilder.between(root.get(Person_.birthDate), getObject().getBirthDateStart(), LocalDate.of(2100, 12, 31)));
        } else if (nonNull(getObject().getBirthDateEnd())) {
            predicates.add(criteriaBuilder.between(root.get(Person_.birthDate), LocalDate.of(1500, 01, 01), getObject().getBirthDateEnd()));
        }

        betweenFullLocalDateTimeSpecification(root, criteriaBuilder, predicates, getObject().getRegisterDateStart(), getObject().getRegisterDateEnd(), Person_.registerAt);
        betweenFullLocalDateTimeSpecification(root, criteriaBuilder, predicates, getObject().getModifiedDateStart(), getObject().getModifiedDateEnd(), Person_.modifiedAt);

        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }

    private void betweenFullLocalDateTimeSpecification(Root<PersonFilter> root, CriteriaBuilder criteriaBuilder, List<Predicate> predicates, LocalDateTime registerDateStart, LocalDateTime registerDateEnd, SingularAttribute<Person, LocalDateTime> registerAt) {
        if (nonNull(registerDateStart) && nonNull(registerDateEnd)) {
            predicates.add(criteriaBuilder.between(root.get(registerAt), registerDateStart, registerDateEnd));
        } else if (nonNull(registerDateStart)) {
            predicates.add(criteriaBuilder.between(root.get(registerAt), registerDateStart, LocalDateTime.of(2100, 12, 31, 23, 59, 59)));
        } else if (nonNull(registerDateEnd)) {
            predicates.add(criteriaBuilder.between(root.get(registerAt), LocalDateTime.of(1500, 01, 01, 0, 0, 0), registerDateEnd));
        }
    }
}
