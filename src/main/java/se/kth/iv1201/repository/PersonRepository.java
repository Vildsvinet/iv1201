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
 * <p>
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


    @Query("select p from Person p RIGHT JOIN Availability a ON a.person_id = p.id")
    List<Person> findAllApplications();
}
