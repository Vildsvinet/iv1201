package se.kth.iv1201.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import se.kth.iv1201.domain.Person;

import java.util.List;
import java.util.Optional;

/**
 * This is a repository that is called from the service layer.
 * Handles database accesses related to persons.
 *
 * Transaction management:
 * using Spring's Transaction manager and applying a rule that, only current transactions
 * can call code contained in this repo. Otherwise, an exception will be thrown.
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface PersonRepository extends JpaRepository<Person, Integer> {

    Optional<Person> findPersonByUsername(String username);
    @Override
    List<Person> findAll();
    @Override
    Person save(Person person);


    /**
     * Queries the database for people who are available to work in 2021.
     * @return A list of Person objects.
     */
    @Query("SELECT p FROM Person p RIGHT JOIN Availability a ON a.person_id = p.id WHERE DATE_PART('year', a.from_date) = '2021'")
    List<Person> findAllApplications();
}
