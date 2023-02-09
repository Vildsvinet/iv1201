package se.kth.iv1201.example.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import se.kth.iv1201.example.domain.IllegalDatabaseAccessException;
import se.kth.iv1201.example.domain.Person;
import se.kth.iv1201.example.domain.PersonDTO;
import se.kth.iv1201.example.repository.PersonRepository;

@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
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
