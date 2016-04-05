package com.sb.intro.filters;

import com.sb.intro.entities.Client;
import com.sb.intro.entities.Client_;
import com.sb.intro.model.ClientResults;
import com.sb.intro.repositories.ClientRepository;
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

/**
 * Created by livious on 2015/07/09.
 */
import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Repository
@Scope(SCOPE_PROTOTYPE)
public class ClientFilter implements Filter<Client, ClientResults, ClientFilter> {

    private String uuid;
    private String name;

    @Autowired
    private ClientRepository repository;

    private PageRequest sortable = new PageRequest(0, 10, new Sort(ASC, "addedOn"));
    private ClientResults results = new ClientResults();

    private long total;
    protected List<Client> filtered = new ArrayList<Client>();

    public ClientFilter with(final String name) {
        this.name = name;
        return this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public ClientFilter sort(final Integer page, final Integer size, final String sort, final Sort.Direction order) {
        sortable = new PageRequest(page - 1, size, new Sort(order, sort));

        return  this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public ClientFilter filter() {
        Page<Client> results = repository.findAll(new ClientSpecification(), sortable);
        filtered = results.getContent();
        total = results.getTotalElements();

        return this;
    }

    @Override
    public ClientFilter andConvert() {
        results.setDisplayedResults(filtered.size());
        results.setTotalResults(total);
        results.setResults(filtered);

        return this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Client> entities() {
        return filtered;
    }

    public ClientFilter with() {
        return null;
    }

    @Override
    public ClientResults results() {
        return results;
    }

    @Override
    public Long totalEntities() {
        return total;
    }

    class ClientSpecification extends EnhancedSpecification<Client> {

        @Override
        public Predicate toPredicate(final Root<Client> root, final CriteriaQuery<?> query, final CriteriaBuilder cb) {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(withAndLikePredicate(cb, root.get(Client_.uuid), uuid));
            predicates.add(withAndLikePredicate(cb, root.get(Client_.name), name));

            Collection<Predicate> filteredPredicates = filterPredicates(cb, predicates);
            if (!filteredPredicates.isEmpty()) {
                query.where(filteredPredicates.toArray(new Predicate[filteredPredicates.size()])).distinct(true);
            }

            return query.getRestriction();
        }
    }
}
