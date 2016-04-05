package com.sb.intro.services;


import com.sb.intro.converters.EventConverter;
import com.sb.intro.model.Event;
import com.sb.intro.repositories.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by livious on 2015/07/05.
 */
@Service
public class EventService {

    private static final Logger log = LoggerFactory.getLogger(EventService.class);

    private EventRepository eventRepository;
    private EventConverter eventConverter;

    @Autowired
    public EventService(final EventRepository eventRepository,
                         final EventConverter eventConverter) {
        this.eventRepository = eventRepository;
        this.eventConverter = eventConverter;
    }

//    public com.sb.intro.entities.Event domain(final String uuid) {
//        return eventRepository.findByUuid(uuid).get(); //.orElseThrow(() -> new DoesNotExistsException(Client.class));
//    }

//    public Event get(final String uuid) {
//        return eventConverter.toModel(domain(uuid)).get();
//    }

//    public void remove(final String uuid) {
//        eventRepository.delete(domain(uuid));
//    }

//    protected void validate(final Event newClient) {
//        if (eventRepository.findByName(newClient.getEventName()).isPresent()) {
////            throw new AlreadyExistsException(com.sb.intro.entities.Client.class);
//        }
//    }
}
