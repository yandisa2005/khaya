package com.sb.intro.resource;

import com.sb.intro.filters.EventFilter;
import com.sb.intro.filters.Filter;
import com.sb.intro.model.Event;
import com.sb.intro.model.EventResults;
import com.sb.intro.services.EventService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by livious on 2015/07/05.
 */
@RestController
@Api(value = "events", description = "Resource for managing events.")
public class EventResource {


    private static final Logger log = LoggerFactory.getLogger(EventResource.class);

//    @Autowired
//    private ObjectFactory<EventFilter> eventFilter;

    @Autowired
    private EventService eventService;
    @Autowired
    private ObjectFactory<EventFilter> eventFilter;

    @Autowired
    public EventResource(final EventService eventService, final ObjectFactory<EventFilter> eventFilter) {
        this.eventService = eventService;
        this.eventFilter = eventFilter;
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    @RequestMapping(method= RequestMethod.GET, value="/events")
    @ApiOperation("GET /events -> return all the events in the database")
    public HttpEntity<EventResults> getAllEvents(
            @ApiParam(value = "Filter by eventName content") @RequestParam(value = "eventName", required = false) String eventName,
            @ApiParam(value = "Filter by venue content") @RequestParam(value = "venue", required = false) String venue,
//            @ApiParam(value = "Filter by eventDate date from") @RequestParam(value = "fromDate", required = false) Long eventDate,
            @ApiParam(value = "Page number of events returned", defaultValue = "1") @RequestParam(value = "page", defaultValue = "1") Integer page,
            @ApiParam(value = "Number of events in a page", defaultValue = "5") @RequestParam(value = "size", defaultValue = "5") Integer size,
            @ApiParam(value = "Sort criteria", defaultValue = "eventDate", allowableValues = "eventDate") @RequestParam(value = "sort", defaultValue = "eventDate") String sort,
            @ApiParam(value = "Direction to order sort property", defaultValue = "DESC", allowableValues = "ASC,DESC") @RequestParam(value = "order", defaultValue = "DESC", required = false) Sort.Direction order
    ){

        Filter filteredMessages= eventFilter.getObject().with(eventName, venue).sort(page, size, sort, order).filter();

        final EventResults results =(EventResults)filteredMessages.andConvert().results();

        HttpEntity <EventResults> entity = new HttpEntity<>(results);

        log.info("********************************** Rest call made on events");

        return entity;
    }

//    @RequestMapping(value = "/{uuid}", method = GET)
//    @ApiOperation("GET /{uuid} -> Return a specific alert Rule's details")
//    public Event find(@PathVariable final String uuid) {
//        return eventService.get(uuid);
//    }
}

