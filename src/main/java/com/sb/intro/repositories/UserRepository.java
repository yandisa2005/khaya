package com.sb.intro.repositories;

import com.sb.intro.entities.User;
import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA repository for the User entity.
 */
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor {

    Optional<User> findOneByActivationKey(String activationKey);

    List<User> findAllByActivatedIsFalseAndCreatedDateBefore(DateTime dateTime);

    Optional<User> findOneByEmail(String email);

    Optional<User> findOneByUsername(String username);

    @Query("select u from User u left join fetch u.authorities where u.username = ?1")
    Optional<User> findOneByUsernameWithAuthorities(String username);

    void delete(User t);
}
