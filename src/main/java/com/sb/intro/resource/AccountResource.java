package com.sb.intro.resource;

import com.sb.intro.entities.Authority;
import com.sb.intro.entities.PersistentToken;
import com.sb.intro.model.User;
import com.sb.intro.repositories.PersistentTokenRepository;
import com.sb.intro.repositories.UserRepository;
import com.sb.intro.security.SecurityUtils;
import com.sb.intro.services.UserService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST controller for managing the current user's account.
 */
@Api(value = "api")
@RestController
@RequestMapping("/api")
public class AccountResource {

    private final Logger log = LoggerFactory.getLogger(AccountResource.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PersistentTokenRepository persistentTokenRepository;

    /**
     * GET  /account -> get the current user.
     */
    @RequestMapping(value = "/account",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("GET  /account -> get the current user.")
    public ResponseEntity<User> getAccount() {
        return Optional.ofNullable(userService.getUserWithAuthorities())
                .map(user -> new ResponseEntity<>(
                        new User(
                                user.getUsername(),
                                null,
                                user.getFirstName(),
                                user.getLastName(),
                                user.getEmail(),
                                user.getLangKey(),
                                user.getAuthorities().stream().map(Authority::getName).collect(Collectors.toList())),
                        HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    /**
     * POST  /account -> update the current user information.
     */
    @RequestMapping(value = "/account",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("POST  /account -> update the current user information.")
    public ResponseEntity<String> saveAccount(@RequestBody User user) {
        return userRepository
                .findOneByUsername(user.getLogin())
                .filter(u -> u.getUsername().equals(SecurityUtils.getCurrentLogin()))
                .map(u -> {
                    userService.updateUserInformation(user.getFirstName(), user.getLastName(), user.getEmail());
                    return new ResponseEntity<String>(HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }


    /**
     * GET  /account/sessions -> get the current open sessions.
     */
    @RequestMapping(value = "/account/sessions",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("GET  /account/sessions -> get the current open sessions.")
    public ResponseEntity<List<PersistentToken>> getCurrentSessions() {
        return userRepository.findOneByUsername(SecurityUtils.getCurrentLogin())
                .map(user -> new ResponseEntity<>(
                        persistentTokenRepository.findByUser(user),
                        HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    /**
     * DELETE  /account/sessions?series={series} -> invalidate an existing session.
     */
    @RequestMapping(value = "/account/sessions/{series}",
            method = RequestMethod.DELETE)
    @ApiOperation("DELETE  /account/sessions?series=:series -> invalidate an existing session.")
    public void invalidateSession(@PathVariable String series) throws UnsupportedEncodingException {
        String decodedSeries = URLDecoder.decode(series, "UTF-8");
        userRepository.findOneByUsername(SecurityUtils.getCurrentLogin()).ifPresent(u -> {
            persistentTokenRepository.findByUser(u).stream()
                    .filter(persistentToken -> StringUtils.equals(persistentToken.getSeries(), decodedSeries))
                    .findAny().ifPresent(t -> persistentTokenRepository.delete(decodedSeries));
        });
    }
}
