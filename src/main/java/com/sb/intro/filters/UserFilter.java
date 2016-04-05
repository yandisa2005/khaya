package com.sb.intro.filters;

import com.sb.intro.converters.UserConverter;
import com.sb.intro.entities.User;
import com.sb.intro.entities.User_;
import com.sb.intro.model.UserResults;
import com.sb.intro.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;
import static org.springframework.data.domain.Sort.Direction.ASC;

@Repository
@Scope(SCOPE_PROTOTYPE)
public class UserFilter implements Filter<User, UserResults, UserFilter> {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserConverter userConverter;

    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private boolean activated;
    private String langKey;
    private String activationKey;

    private PageRequest sortable = new PageRequest(0, 10, new Sort(ASC, "firstName"));

    private Long total;
    private List<User> filtered = new ArrayList<>();
    private UserResults results = new UserResults();

    public UserFilter with(final String username,
                           final String firstName,
                           final String lastName,
                           final String email,
                           final Boolean activated,
                           final String langKey,
                           final String activationKey) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.activated = activated;
        this.langKey = langKey;
        this.activationKey = activationKey;

        return this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public UserFilter sort(final Integer page, final Integer size, final String sort, final Sort.Direction order) {
        sortable = new PageRequest(page - 1, size, new Sort(order, sort));

        return this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public UserFilter filter() {
        Page<User> results = repository.findAll(new UserSpecification(), sortable);
        filtered = results.getContent();
        total = results.getTotalElements();

        return this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> entities() {
        return filtered;
    }

    @Override
    public Long totalEntities() {
        return total;
    }

    @Override
    public UserFilter andConvert() {
        results.setDisplayedResults(filtered.size());
        results.setTotalResults(total);
        results.setResults(userConverter.toModels(entities()));

        return this;
    }

    @Override
    public UserResults results() {
        return results;
    }

    public UserFilter with() {
        return null;
    }

    class UserSpecification extends EnhancedSpecification<User> {

        @Override
        public Predicate toPredicate(final Root<User> root, final CriteriaQuery<?> query, final CriteriaBuilder cb) {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(withAndLikePredicate(cb, root.get(User_.username), username));
            predicates.add(withAndLikePredicate(cb, root.get(User_.firstName), firstName));
            predicates.add(withAndLikePredicate(cb, root.get(User_.lastName), lastName));
            predicates.add(withAndEqualPredicate(cb, root.get(User_.activated), activated));
            predicates.add(withAndLikePredicate(cb, root.get(User_.activationKey), activationKey));
            predicates.add(withAndLikePredicate(cb, root.get(User_.email), email));
            predicates.add(withAndLikePredicate(cb, root.get(User_.langKey), langKey));

            Collection<Predicate> filteredPredicates = filterPredicates(cb, predicates);
            if (!filteredPredicates.isEmpty()) {
                query.where(filteredPredicates.toArray(new Predicate[filteredPredicates.size()])).distinct(true);
            }

            return query.getRestriction();
        }
    }
}
