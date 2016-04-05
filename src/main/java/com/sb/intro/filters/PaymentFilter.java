package com.sb.intro.filters;

import com.sb.intro.entities.Event_;
import com.sb.intro.entities.Payment;
import com.sb.intro.entities.Payment_;
import com.sb.intro.model.PaymentResults;
import com.sb.intro.repositories.EventRepository;
import com.sb.intro.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static org.springframework.data.domain.Sort.Direction.ASC;

/**
 * Created by livious on 2015/07/09.
 */
public class PaymentFilter implements Filter<Payment, PaymentResults, PaymentFilter> {

    private String name;
    private Date eventDate;
    private String venue;

    @Autowired
    private PaymentRepository repository;

    private PageRequest sortable = new PageRequest(0, 10, new Sort(ASC, "addedOn"));
    private PaymentResults results = new PaymentResults();

    private long total;
    protected List<Payment> filtered = new ArrayList<Payment>();

    @SuppressWarnings("unchecked")
    @Override
    public PaymentFilter sort(final Integer page, final Integer size, final String sort, final Sort.Direction order) {
        sortable = new PageRequest(page - 1, size, new Sort(order, sort));

        return  this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public PaymentFilter filter() {
        Page<Payment> results = repository.findAll(new PaymentSpecification(), sortable);
        filtered = results.getContent();
        total = results.getTotalElements();

        return this;
    }

    @Override
    public PaymentFilter andConvert() {
        results.setDisplayedResults(filtered.size());
        results.setTotalResults(total);
        results.setResults(filtered);

        return this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Payment> entities() {
        return filtered;
    }

    @Override
    public PaymentResults results() {
        return results;
    }

    @Override
    public Long totalEntities() {
        return total;
    }

    class PaymentSpecification extends EnhancedSpecification<Payment> {

        @Override
        public Predicate toPredicate(final Root<Payment> root, final CriteriaQuery<?> query, final CriteriaBuilder cb) {
            List<Predicate> predicates = new ArrayList<>();

//            predicates.add(withAndLikePredicate(cb, root.get(Event_.uuid), uuid));
//            predicates.add(withAndLikePredicate(cb, root.get(Payment_.amount), amount));
//            predicates.add(withAndLikePredicate(cb, root.get(Event_.venue), venue));

            Collection<Predicate> filteredPredicates = filterPredicates(cb, predicates);
            if (!filteredPredicates.isEmpty()) {
                query.where(filteredPredicates.toArray(new Predicate[filteredPredicates.size()])).distinct(true);
            }

            return query.getRestriction();
        }
    }
}
