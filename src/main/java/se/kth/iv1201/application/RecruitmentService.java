package se.kth.iv1201.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import se.kth.iv1201.domain.IllegalDatabaseAccessException;
import se.kth.iv1201.domain.Person;
import se.kth.iv1201.domain.PersonDTO;
import se.kth.iv1201.repository.PersonRepository;

/**
 * Transactions:
 * All code in this class will be managed in a transaction.
 * Transaction starts when code in this class is called, and ends when these defined methods return or
 * are subject to transaction rollback.
 */
@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)   // rollback when any exception in transaction happens.
@Service
public class RecruitmentService {

    @Autowired
    private PersonRepository personRepo;

    public PersonDTO createPerson(String name, String surname, String pnr, String email, String password, int role_id, String username ) throws IllegalDatabaseAccessException {
        Person personEntity = personRepo.findPersonByUsername(username);
        if (personEntity != null) {
            throw new IllegalDatabaseAccessException("Username already exist.");
        }
        return personRepo.save(new Person(name, surname, pnr, email, password, role_id, username));
    }

    public PersonDTO loginPerson(String username, String password ) throws IllegalDatabaseAccessException {
        Person personEntity = personRepo.findPersonByUsername(username);
        if (personEntity == null) {
            throw new IllegalDatabaseAccessException("Username does not exist.");
        } else if(personEntity.getUsername().equals(username) && personEntity.getPassword().equals(password)){
            return personEntity;
        } else
            throw new IllegalDatabaseAccessException("Incorrect username and/or password.");
    }
}
