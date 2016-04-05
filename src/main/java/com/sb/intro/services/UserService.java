package com.sb.intro.services;

import com.sb.intro.entities.User;
import com.sb.intro.repositories.PersistentTokenRepository;
import com.sb.intro.repositories.UserRepository;
import com.sb.intro.resource.exceptions.DoesNotExistsException;
import com.sb.intro.security.SecurityUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PersistentTokenRepository persistentTokenRepository;

    public void updateUserInformation(String firstName, String lastName, String email) {
        userRepository.findOneByUsername(SecurityUtils.getCurrentLogin()).ifPresent(u -> {
            u.setFirstName(firstName);
            u.setLastName(lastName);
            u.setEmail(email);
            userRepository.save(u);
            log.debug("Changed Information for User: {}", u);
        });
    }

    @Transactional(readOnly = true)
    public User getUserWithAuthorities() {
        User currentUser = userRepository.findOneByUsername(SecurityUtils.getCurrentLogin()).get();
        currentUser.getAuthorities().size(); // eagerly load the association
        return currentUser;
    }

    @Transactional(readOnly = true)
    public User getUser(final String username) {
        return userRepository.findOneByUsername(username).orElseThrow(() -> new DoesNotExistsException(User.class));
    }

    @Transactional(readOnly = true)
    public Optional<User> findUser(final String username) {
        return userRepository.findOneByUsername(username);
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void removeOldPersistentTokens() {
        LocalDate now = new LocalDate();
        persistentTokenRepository.findByTokenDateBefore(now.minusMonths(1)).stream().forEach(token -> {
            log.debug("Deleting token {}", token.getSeries());
            User user = token.getUser();
            user.getPersistentTokens().remove(token);
            persistentTokenRepository.delete(token);
        });
    }

    @Scheduled(cron = "0 0 1 * * ?")
    public void removeNotActivatedUsers() {
        DateTime now = new DateTime();
        List<User> users = userRepository.findAllByActivatedIsFalseAndCreatedDateBefore(now.minusDays(3));
        for (User user : users) {
            log.debug("Deleting not activated user {}", user.getUsername());
            userRepository.delete(user);
        }
    }

    @Transactional(readOnly = true)
    public List<User> getAll(){
        return  userRepository.findAll();
    }
}
