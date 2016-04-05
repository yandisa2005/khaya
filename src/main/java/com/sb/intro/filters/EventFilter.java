package com.sb.intro.filters;

import com.sb.intro.entities.Event;
import com.sb.intro.entities.Event_;
import com.sb.intro.model.EventResults;
import com.sb.intro.repositories.EventRepository;
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
import java.util.Date;
import java.util.List;

/**
 * Created by livious on 2015/07/09.
 */
import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Repository
@Scope(SCOPE_PROTOTYPE)
public class EventFilter implements Filter<Event, EventResults, EventFilter> {

    private String eventName;
    private Date eventDate;
    private String venue;

    @Autowired
    private EventRepository eventRepository;

    private PageRequest sortable = new PageRequest(0, 10, new Sort(ASC, "addedOn"));
    private EventResults results = new EventResults();

    private long total;
    protected List<Event> filtered = new ArrayList<Event>();

    public EventFilter with(final String eventName, final String venue) {
        this.eventName = eventName;
        this.venue = venue;

  //      if (eventDate != null) this.eventDate = new Date(eventDate);
        return this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public EventFilter sort(final Integer page, final Integer size, final String sort, final Sort.Direction order) {
        sortable = new PageRequest(page - 1, size, new Sort(order, sort));

        return  this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public EventFilter filter() {
        Page<Event> results = eventRepository.findAll(new EventSpecification(), sortable);
        filtered = results.getContent();
        total = results.getTotalElements();

        return this;
    }

    @Override
    public EventFilter andConvert() {
        results.setDisplayedResults(filtered.size());
        results.setTotalResults(total);
        results.setResults(filtered);

        return this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Event> entities() {
        return filtered;
    }

    @Override
    public EventResults results() {
        return results;
    }

    @Override
    public Long totalEntities() {
        return total;
    }

    class EventSpecification extends EnhancedSpecification<Event> {

        @Override
        public Predicate toPredicate(final Root<Event> root, final CriteriaQuery<?> query, final CriteriaBuilder cb) {
            List<Predicate> predicates = new ArrayList<>();

//            predicates.add(withAndLikePredicate(cb, root.get(Event_.uuid), uuid));
            predicates.add(withAndLikePredicate(cb, root.get(Event_.eventName), eventName));
            predicates.add(withAndLikePredicate(cb, root.get(Event_.venue), venue));

            Collection<Predicate> filteredPredicates = filterPredicates(cb, predicates);
            if (!filteredPredicates.isEmpty()) {
                query.where(filteredPredicates.toArray(new Predicate[filteredPredicates.size()])).distinct(true);
            }

            return query.getRestriction();
        }
    }
}
