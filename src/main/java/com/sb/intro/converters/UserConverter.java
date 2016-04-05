package com.sb.intro.converters;

import com.sb.intro.entities.Authority;
import com.sb.intro.model.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Component
@ProvidesConverter({User.class, com.sb.intro.entities.User.class})
public class UserConverter implements Converter<User, com.sb.intro.entities.User> {

    @Override
    public Optional<User> toModel(final com.sb.intro.entities.User domain) {
        User model = new User(
                domain.getUsername(),
                domain.getPassword(),
                domain.getFirstName(),
                domain.getLastName(),
                domain.getEmail(),
                domain.getLangKey(),
                domain.getAuthorities()
                        .stream()
                        .map(Authority::getName)
                        .collect(toList()));

        return Optional.of(model);
    }

}
