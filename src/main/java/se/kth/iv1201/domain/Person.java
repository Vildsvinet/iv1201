package se.kth.iv1201.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity // By annotating with entity I indicate to Spring that I will want to persist this class in a db
@Table(name = "Person")
public class Person implements PersonDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PERSON_ID")
    private int id;

    @NotBlank()
    @Size(min = 2, max = 50)
    @Column(name = "USERNAME")
    private String username;

    @NotBlank()
    @Size(min = 4, max = 255)
    @Column(name = "PASSWORD")
    private String password;

    @NotBlank()
    @Size(min = 2, max = 50)
    private String name;

    @NotBlank()
    @Size(min = 2, max = 50)
    private String surname;

    @NotBlank()
    @Pattern(regexp = "\\d{8}[-\\.]\\d{4}")
    private String pnr;

    @NotBlank()
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
