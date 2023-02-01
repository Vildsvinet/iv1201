package se.kth.iv1201.example.domain;


import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "Person")
public class Person implements PersonDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @PositiveOrZero(message = "{acct.balance.negative}")
    @Column(name = "USERNAME")
    private String username;

    @PositiveOrZero(message = "{acct.balance.negative}")
    @Column(name = "PASSWORD")
    private String password;

    /**
     * Required by JPA, should not be used.
     */
    protected Person() {
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
