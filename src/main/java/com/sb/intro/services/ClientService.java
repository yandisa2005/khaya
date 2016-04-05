package com.sb.intro.services;

import com.sb.intro.converters.ClientConverter;
import com.sb.intro.model.Client;
import com.sb.intro.repositories.ClientRepository;
import com.sb.intro.resource.exceptions.AlreadyExistsException;
import com.sb.intro.resource.exceptions.DoesNotExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by livious on 2015/07/05.
 */
@Service
public class ClientService {

    private static final Logger log = LoggerFactory.getLogger(ClientService.class);

    private ClientRepository clientRepository;
    private ClientConverter clientConverter;

    @Autowired
    public ClientService(final ClientRepository clientRepository,
                            final ClientConverter clientConverter) {
        this.clientRepository = clientRepository;
        this.clientConverter = clientConverter;
    }

    public com.sb.intro.entities.Client domain(final String uuid) {
        return clientRepository.findByUuid(uuid).get(); //.orElseThrow(() -> new DoesNotExistsException(Client.class));
    }

    public Client get(final String uuid) {
        return clientConverter.toModel(domain(uuid)).get();
    }

//    public AlertRule add(final AlertRule newRule) {
//        cib.jas.odin.domain.AbstractAlertRule rule = ruleConverter.toDomain(newRule).get();
//
//        validate(newRule);
//        rule.setCreatedBy(getCurrentLogin());
//        rule = ruleRepository.save(rule);
//
//        log.debug("Added new alert rule: {}", rule);
//        return get(rule.getUuid());
//    }
//
//    public AlertRule edit(final String uuid, final AlertRule rule) {
//        cib.jas.odin.domain.AbstractAlertRule existingRule = domain(uuid);
//
//        if (existingRule instanceof AlertTimeRule) {
//            AlertTimeRule timeRule = (AlertTimeRule) existingRule;
//
//            timeRule.setName(rule.getName());
//            timeRule.setDescription(rule.getDescription());
//            timeRule.setItineraryStart(new LocalTime(rule.getItineraryStart()));
//            timeRule.setItineraryEnd(new LocalTime(rule.getItineraryEnd()));
//            ruleRepository.save(timeRule);
//
//        }
//
//        return get(uuid);
//    }

    public void remove(final String uuid) {
        clientRepository.delete(domain(uuid));
    }

    protected void validate(final Client newClient) {
        if (clientRepository.findByName(newClient.getName()).isPresent()) {
//            throw new AlreadyExistsException(com.sb.intro.entities.Client.class);
        }
    }
}
