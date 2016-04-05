package com.sb.intro.converters;

import com.sb.intro.model.Event;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by livious on 2015/12/09.
 */

@Component
@ProvidesConverter({Event.class, com.sb.intro.entities.Event.class})
public class EventConverter implements Converter<Event, com.sb.intro.entities.Event> {

    @Override
    public Optional<Event> toModel(final com.sb.intro.entities.Event domain) {
        Event model = new Event(
                domain.getEventName(),
                domain.getVenue(),
                domain.getEventDate(),
                domain.getAmount(),
                domain.getTotalExpenditure(),
                domain.getTaxCharge(),
                domain.getCreatedBy(),
                domain.getStatus());
        return Optional.of(model);
    }

    @Override
    public Optional<com.sb.intro.entities.Event> toDomain(final Event model) {
        return Optional.of(new com.sb.intro.entities.Event(
                model.getEventName(),
                model.getVenue(),
                model.getEventDate(),
                model.getAmount(),
                model.getTotalExpenditure(),
                model.getTaxCharge(),
                model.getCreatedBy(),
                model.getStatus()));
    }
}
