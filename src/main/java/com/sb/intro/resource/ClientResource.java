package com.sb.intro.resource;

import com.sb.intro.filters.ClientFilter;
import com.sb.intro.filters.Filter;

import com.sb.intro.model.ClientResults;
import com.wordnik.swagger.annotations.Api;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by livious on 2015/07/05.
 */
@RestController
@Api(value = "clients", description = "Resource for managing clients.")
public class ClientResource {

    private static final Logger log = LoggerFactory.getLogger(ClientResource.class);

    @Autowired
    private ObjectFactory<ClientFilter> clientFilter;

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    @RequestMapping(method= RequestMethod.GET, value="/clients")
    @ApiOperation("GET /clients -> return all the clients in the database")
    public HttpEntity<ClientResults> getAllClients(
            @ApiParam(value = "Filter by client content") @RequestParam(value = "name", required = false) String name,
            @ApiParam(value = "Page number of clients returned", defaultValue = "1") @RequestParam(value = "page", defaultValue = "1") Integer page,
            @ApiParam(value = "Number of clients in a page", defaultValue = "5") @RequestParam(value = "size", defaultValue = "5") Integer size,
            @ApiParam(value = "Sort criteria", defaultValue = "name", allowableValues = "name") @RequestParam(value = "sort", defaultValue = "name") String sort,
            @ApiParam(value = "Direction to order sort property", defaultValue = "DESC", allowableValues = "ASC,DESC") @RequestParam(value = "order", defaultValue = "DESC", required = false) Sort.Direction order
    ){

        Filter filteredMessages= clientFilter.getObject().with(name).sort(page, size, sort, order).filter();

        final ClientResults results =(ClientResults)filteredMessages.andConvert().results();

        HttpEntity <ClientResults> entity = new HttpEntity<>(results);

        log.info("********************************** Rest call made on clients");

        return entity;
    }
}
