package se.kth.iv1201.example.domain;


import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "Person")
public class Person implements PersonDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "PERSON_ID")
    private long id;

    @PositiveOrZero(message = "{...}")
    @Column(name = "USERNAME")
    private String username;

    @PositiveOrZero(message = "{...}")
    @Column(name = "PASSWORD")
    private String password;

    /**
     * Required by JPA, should not be used.
     */
    protected Person() {
    }

    public Person(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

}
