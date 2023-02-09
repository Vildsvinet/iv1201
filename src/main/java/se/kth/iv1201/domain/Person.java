package se.kth.iv1201.domain;


import javax.persistence.*;

@Entity // By annotating with entity I indicate to Spring that I will want to persist this class in a db
@Table(name = "Person")
public class Person implements PersonDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "PERSON_ID")
    private int id;


    @Column(name = "USERNAME")
    private String username;


    @Column(name = "PASSWORD")
    private String password;

    private String name;
    private String surname;

    private String pnr;

    private String email;

    private int role_id;



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

}
