package se.kth.iv1201.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

/**
 * Handles the data associated with a person. Corresponds to a table Person in the database.
 * Sets conditions for the different attributes a person can have, eg length of username.
 */
@Entity //Annotating with entity indicates to Spring that this class should persist in a db
@Table(name = "Person")
public class Person implements PersonDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PERSON_ID")
    private int id;

    @Size(min = 2, max = 50)
    @Column(name = "USERNAME")
    private String username;

    @Size(min = 4, max = 255)
    @Column(name = "PASSWORD")
    private String password;

    @Size(min = 2, max = 50)
    private String name;

    @Size(min = 2, max = 50)
    private String surname;

    //validate that this is a Swedish personal number
    @Pattern(regexp = "^(19|20)\\d{2}(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])[-\\.]\\d{4}$")
    private String pnr;

    @Email
    @Size(min = 3, max = 50)
    private String email;

    private int role_id;
    private int applicationStatus;

    /**
     * Required by JPA, if there is a constructor with args, you must have a base constructor
     */
    protected Person() {
    }

    public Person(String name, String surname, String pnr, String email, String password, int role_id, String username) {
        this.name = name;
        this.surname = surname;
        this.pnr = pnr;
        this.email = email;
        this.password = password;
        this.role_id = role_id;
        this.username = username;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public int getRole_id() {
        return role_id;
    }

    @Override
    public int getApplicationStatus() {
        return applicationStatus;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSurname() {
        return surname;
    }
}
