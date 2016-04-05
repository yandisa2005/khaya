package com.sb.intro.filters;

import com.google.common.collect.Collections2;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import java.util.Collection;
import java.util.List;

import static com.google.common.base.Optional.fromNullable;
import static java.lang.String.format;
import static java.util.Arrays.asList;

public abstract class EnhancedSpecification<T> implements Specification<T> {

    private static final Logger log = LoggerFactory.getLogger(EnhancedSpecification.class);

    protected Predicate withAndLikePredicate(final CriteriaBuilder cb, final Expression<String> expression, final String value) {
        return withLikePredicate(cb, expression, value, true);
    }

    protected Predicate withOrLikePredicate(final CriteriaBuilder cb, final Expression<String> expression, final String value) {
        return withLikePredicate(cb, expression, value, false);
    }

    protected Predicate withLikePredicate(final CriteriaBuilder cb, final Expression<String> expression, final String value, final boolean andOr) {
        log.trace("Building like predicate for expression [{}] with value: {}", expression.getJavaType(), value);
        Predicate predicate = null;
        if (StringUtils.isNotBlank(value)) {
            Predicate like = cb.like(cb.upper(expression), makeLikeable(value.toUpperCase()));
            if (andOr) {
                predicate = cb.and(like);
            } else {
                predicate = cb.or(like);
            }
        }

        return predicate;
    }

    protected <T> Predicate withAndEqualPredicate(final CriteriaBuilder cb, final Expression<T> expression, final T value) {
        Predicate predicate = null;
        if (value != null) {
            predicate = cb.and(cb.equal(expression, value));
        }

        return predicate;
    }

    protected <T> Predicate withNullPredicate(final CriteriaBuilder cb, final Expression<T> expression, final Boolean value) {
        Predicate predicate = null;
        if (value != null) {
            if (value) {
                predicate = cb.and(cb.isNotNull(expression));
            } else {
                predicate = cb.and(cb.isNull(expression));
            }
        }

        return predicate;
    }

    protected <T extends Collection<?>> Predicate withNotEmptyPredicate(final CriteriaBuilder cb, final Expression<T> expression, final Boolean value) {
        Predicate predicate = null;
        if (value != null) {
            if (value) {
                predicate = cb.and(cb.isNotEmpty(expression));
            } else {
                predicate = cb.and(cb.isEmpty(expression));
            }
        }

        return predicate;
    }

    protected <T extends Comparable<T>> Predicate withAndBetweenPredicate(final CriteriaBuilder cb, final Expression<T> expression, final T from, final T to) {
        Predicate predicate = null;
        if (from != null && to != null) {
            predicate = cb.and(cb.between(expression, from, to));
        } else {
            if (from != null) {
                predicate = cb.and(cb.greaterThanOrEqualTo(expression, from));
            } else if (to != null) {
                predicate = cb.and(cb.lessThanOrEqualTo(expression, to));
            }
        }

        return predicate;
    }

    protected <T extends Comparable<T>> Predicate withGreaterLesserThanPredicate(final CriteriaBuilder cb, final Expression<T> greaterThanExpression, final Expression<T> lesserThanExpression, final T from, final T to) {
        Predicate predicate = null;
        if (from != null && to != null) {
            predicate = cb.and(filterPredicates(cb, cb.greaterThanOrEqualTo(greaterThanExpression, from), cb.lessThanOrEqualTo(lesserThanExpression, to)));
        } else {
            if (from != null) {
                predicate = cb.and(cb.greaterThanOrEqualTo(greaterThanExpression, from));
            } else if (to != null) {
                predicate = cb.and(cb.lessThanOrEqualTo(lesserThanExpression, to));
            }
        }

        return predicate;
    }

    protected Predicate[] filterPredicates(final CriteriaBuilder cb, final Predicate... predicates) {
        return filterPredicates(cb, asList(predicates)).toArray(new Predicate[predicates.length]);
    }

    protected Collection<Predicate> filterPredicates(final CriteriaBuilder cb, final List<Predicate> predicates) {
        return Collections2.transform(predicates, predicate -> {
            Predicate transformedPredicate = predicate;
            if (transformedPredicate == null) {
                transformedPredicate = cb.equal(cb.literal(1), 1);
            }

            return transformedPredicate;
        });
    }

    protected String makeLikeable(final String value) {
        String likeable = format("%%%s%%", fromNullable(value).or(""));

        log.trace("Made this value likeable: {} -> {}", value, likeable);
        return likeable;
    }
}
