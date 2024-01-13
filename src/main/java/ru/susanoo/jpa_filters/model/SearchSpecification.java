package ru.susanoo.jpa_filters.model;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class SearchSpecification<X> {

    private Specification<X> specification;

    public void init(SearchDTO searchDTO) {
        for (FilterPackageDTO filterPackage : searchDTO.getFilters()) {
            if (filterPackage.getFilterPackage() != null) {

            }
        }
    }

    public Specification<X> concatFilters(List<FilterDTO> filters, ExpressionType expressionType) {
        return switch (expressionType) {
            case AND -> Specification.allOf(filters.stream().map(this::createFilter).toList());
            case OR -> Specification.anyOf(filters.stream().map(this::createFilter).toList());
        };
    }

    private Specification<X> createFilter(FilterDTO filter) {
        return ((root, cq, cb) -> constructExpression(filter, root, (CriteriaQuery<X>) cq, cb));
    }

    private Predicate constructExpression(FilterDTO filter, Root<X> root, CriteriaQuery<X> cq, CriteriaBuilder cb) {
        return switch (filter.getOperator()) {
            case EQUAL -> cb.equal(root.get(filter.getFieldPath()), filter.getValue());
            case LESS -> cb.lessThan(root.get(filter.getFieldPath()), filter.getValue());
            case NOT_EQUAL -> cb.notEqual(root.get(filter.getFieldPath()), filter.getValue());
            default -> throw new RuntimeException();
        };
    }
}
