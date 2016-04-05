package com.sb.intro.converters;

import com.sb.intro.model.Client;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by livious on 2015/12/09.
 */

@Component
@ProvidesConverter({Client.class, com.sb.intro.entities.Client.class})
public class ClientConverter implements Converter<Client, com.sb.intro.entities.Client> {

//    @Autowired
//    private ClientConverter clientConverter;

    @Override
    public Optional<Client> toModel(final com.sb.intro.entities.Client domain) {
        Client model = new Client(domain.getName(), domain.getCreatedBy());
        return Optional.of(model);
    }

    @Override
    public Optional<com.sb.intro.entities.Client> toDomain(final Client model) {
        return Optional.of(new com.sb.intro.entities.Client(
                model.getName(),
                model.getCretaedBy()));
    }
}
