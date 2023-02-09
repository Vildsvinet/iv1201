package se.kth.iv1201.repository;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import se.kth.iv1201.domain.Person;

import java.util.List;

/**
 * Contains all database access concerning persons.
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY) // Applies only to methods
public interface PersonRepository extends JpaRepository<Person, Integer> {
    /**
     * Returns the person with the specified username, or null if there is no
     * such username.
     *
     * @param username The username of the Person to search for.
     * @return The Person with the specified username, or null if there is no
     *         such Person.
     * @throws IncorrectResultSizeDataAccessException If more than one username with
     *                                                the specified username was
     *                                                found.
     */


    Person findPersonByUsername(String username);
    @Override
    List<Person> findAll();
    @Override
    Person save(Person person);
}
