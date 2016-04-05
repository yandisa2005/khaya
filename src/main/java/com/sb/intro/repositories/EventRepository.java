package com.sb.intro.repositories;

import com.sb.intro.entities.Client;
import com.sb.intro.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by livious on 2015/07/05.
 */

@Repository
public interface EventRepository extends JpaRepository<Event, Long>, JpaSpecificationExecutor {

    Optional<Event> findByUuid(String uuid);

    Optional<Event> findByName(String name);
}
