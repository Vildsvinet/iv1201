package se.kth.iv1201.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import se.kth.iv1201.domain.IllegalDatabaseException;
import se.kth.iv1201.domain.Person;
import se.kth.iv1201.domain.PersonDTO;
import se.kth.iv1201.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

/**
 * This class is the service class for the application.
 *
 * Transactions:
 * All code in this class will be managed in a transaction.
 * Transaction starts when code in this class is called, and ends when these defined methods return or
 * are subject to transaction rollback.
 */
@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)   // rollback when any exception in transaction happens.
@Service
public class RecruitmentService {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private PersonRepository personRepository;

    /**
     * Creates a new person row in the database table Person.
     * Will check if the username is already taken, and if Yes throw an Exception.
     * @param name      the first name as entered by a user trying to create a new account
     * @param surname   the last name/surname
     * @param pnr       personal number/social security number (Swedish only)
     * @param email     the email address of the aspiring user
     * @param password  chosen password
     * @param role_id   whether the created user should be a recruiter (1) or an applicant (2)
     * @param username  chosen username
     * @return A DTO of what is saved in the repository/database
     * @throws IllegalDatabaseException   If the username is already occupied
     */
    public PersonDTO createPerson(String name, String surname, String pnr, String email, String password, int role_id, String username ) throws IllegalDatabaseException {
        Optional<Person> personEntity = personRepository.findPersonByUsername(username);
        if (personEntity.isPresent()) {
            throw new IllegalDatabaseException("Username already exist.");
        }
        return personRepository.save(new Person(name, surname, pnr, email, passwordEncoder.encode(password), role_id, username));
    }

    public Person getPersonByUsername(String username) {
        return personRepository.findPersonByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }

    public List<Person> getAllApplications() {
        return personRepository.findAllApplications();
    }
}
