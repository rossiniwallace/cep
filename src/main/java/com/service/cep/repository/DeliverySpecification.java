package com.service.cep.repository;

import com.service.cep.domian.Delivery;
import com.service.cep.dto.delivery.DeliveryFilter;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;

public class DeliverySpecification {

    public static Specification<Delivery> filterBy(DeliveryFilter filter) {
        return (root, query, criteriaBuilder) -> {
            var predicates = new ArrayList<Predicate>();

            if (filter.trackingCode() != null && !filter.trackingCode().isBlank()) {
                predicates.add(criteriaBuilder.equal(root.get("trackingCode"), filter.trackingCode()));
            }
            if (filter.status() != null && !filter.status().isBlank()) {
                predicates.add(criteriaBuilder.equal(root.get("status"), filter.status()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
