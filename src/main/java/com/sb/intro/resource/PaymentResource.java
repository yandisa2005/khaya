package com.sb.intro.resource;

import com.sb.intro.filters.Filter;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by livious on 2015/07/05.
 */
@Service
public class PaymentResource {

//
//    private static final Logger log = LoggerFactory.getLogger(RestMessageService.class);
//
//    @Autowired
//    private ObjectFactory<MessageFilter> messageFilter;
//
//    @SuppressWarnings("unchecked")
//    @RequestMapping(method = GET, value="/messages")
//    @Transactional(readOnly = true)
//    @ApiOperation("GET /messages -> return all the messages in the database")
//    public HttpEntity<MessageResults> getAllMessages(
//            @ApiParam(value = "Filter by message content") @RequestParam(value = "message", required = false) String message,
//            @ApiParam(value = "Filter by addedOn date from") @RequestParam(value = "fromDate", required = false) Long fromDate,
//            @ApiParam(value = "Filter by addedOn date to") @RequestParam(value = "toDate", required = false) Long toDate,
//            @ApiParam(value = "Page number of messages returned", defaultValue = "1") @RequestParam(value = "page", defaultValue = "1") Integer page,
//            @ApiParam(value = "Number of messages in a page", defaultValue = "5") @RequestParam(value = "size", defaultValue = "5") Integer size,
//            @ApiParam(value = "Sort criteria", defaultValue = "addedOn", allowableValues = "addedOn") @RequestParam(value = "sort", defaultValue = "addedOn") String sort,
//            @ApiParam(value = "Direction to order sort property", defaultValue = "DESC", allowableValues = "ASC,DESC") @RequestParam(value = "order", defaultValue = "DESC", required = false) Sort.Direction order
//    ){
//
//        Filter filteredMessages= messageFilter.getObject().with(message, fromDate, toDate).sort(page, size, sort, order).filter();
//
//        final MessageResults results =(MessageResults)filteredMessages.andConvert().results();
//
//        HttpEntity <MessageResults> entity = new HttpEntity<>(results);
//
//        return entity;
//    }
}

