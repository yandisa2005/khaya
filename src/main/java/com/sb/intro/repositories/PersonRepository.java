package com.sb.intro.repositories;

import com.sb.intro.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by livious on 2015/07/05.
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long >, JpaSpecificationExecutor {
}
