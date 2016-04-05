package com.sb.intro.resource;

import com.sb.intro.entities.User;
import com.sb.intro.filters.UserFilter;
import com.sb.intro.model.UserResults;
import com.sb.intro.repositories.UserRepository;
import com.sb.intro.security.AuthoritiesConstants;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * REST controller for managing users.
 */
@RestController
@RequestMapping("/api")
@Api(value = "api")
public class UserResource {

    private final Logger log = LoggerFactory.getLogger(UserResource.class);

    private UserRepository userRepository;
    private ObjectFactory<UserFilter> userFilter;

    @Autowired
    public UserResource(final UserRepository userRepository, final ObjectFactory<UserFilter> userFilter) {
        this.userRepository = userRepository;
        this.userFilter = userFilter;
    }

    /**
     * GET  /users/:login -> get the "login" user.
     */
    @RequestMapping(value = "/users/{login}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("GET  /users/:login -> get the \"login\" user.")
    @RolesAllowed(AuthoritiesConstants.ADMIN)
    ResponseEntity<User> getUser(@PathVariable String login) {
        log.debug("REST request to get User : {}", login);
        return userRepository.findOneByUsername(login)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/users", method = GET)
    @Transactional(readOnly = true)
    @ApiOperation("GET /users -> find all users with matching criteria")
    public HttpEntity<UserResults> all(
            @ApiParam(value = "Filter by username.") @RequestParam(value = "username", required = false) String username,
            @ApiParam(value = "Filter by first name. Partial filtering is allowed.") @RequestParam(value = "firstName", required = false) String firstName,
            @ApiParam(value = "Filter by last name. Partial filtering is allowed.") @RequestParam(value = "lastName", required = false) String lastName,
            @ApiParam(value = "Filter by activated. Defaults to active users") @RequestParam(value = "email", required = false) String email,
            @ApiParam(value = "Filter by member's username. Partial filtering is allowed.") @RequestParam(value = "activated", required = false, defaultValue = "true") Boolean activated,
            @ApiParam(value = "Filter by langkey. Partial filtering is allowed.") @RequestParam(value = "langKey", required = false) String langkey,
            @ApiParam(value = "Filter by activation key. Partial filtering is allowed.") @RequestParam(value = "activationKey", required = false) String activationKey,
            @ApiParam(value = "Page number of users to return", defaultValue = "1") @RequestParam(value = "page", defaultValue = "1") Integer page,
            @ApiParam(value = "Number users to return for this page", defaultValue = "10") @RequestParam(value = "size", defaultValue = "10") Integer size,
            @ApiParam(value = "Which property to sort by", defaultValue = "name", allowableValues = "firstName,username") @RequestParam(value = "sort", defaultValue = "firstName") String sort,
            @ApiParam(value = "Direction to order sort property", defaultValue = "desc", allowableValues = "asc,desc") @RequestParam(value = "order", defaultValue = "asc") Sort.Direction order) {
        HttpEntity<UserResults> entity;

        final UserFilter filter = userFilter.getObject().with(username, firstName, lastName, email, activated, langkey, activationKey).sort(page, size, sort, order).filter();

        final UserResults results = filter.andConvert().results();
        entity = new HttpEntity<>(results);
        if (results.getDisplayedResults() == 0) {
            entity = new ResponseEntity(NO_CONTENT);
        }

        return entity;
    }
}
