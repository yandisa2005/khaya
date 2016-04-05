package com.sb.intro.repositories;

import com.sb.intro.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

/**
 * Created by livious on 2015/07/05.
 */
public interface ClientRepository extends JpaRepository<Client, Long>, JpaSpecificationExecutor {

    Optional<Client> findByUuid(String uuid);

    Optional<Client> findByName(String name);
}
