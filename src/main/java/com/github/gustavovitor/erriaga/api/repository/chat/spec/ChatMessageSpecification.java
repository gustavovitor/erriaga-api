package com.github.gustavovitor.erriaga.api.repository.chat.spec;

import com.github.gustavovitor.erriaga.api.repository.chat.spec.filter.ChatMessageFilter;
import com.github.gustavovitor.maker.repository.SpecificationBase;

import javax.management.ReflectionException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ChatMessageSpecification extends SpecificationBase<ChatMessageFilter> {

    public ChatMessageSpecification(ChatMessageFilter object) throws ReflectionException {
        super(object);
    }

    @Override
    public Predicate toPredicate(Root<ChatMessageFilter> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
