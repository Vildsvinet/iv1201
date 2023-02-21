package se.kth.iv1201.domain;

import jakarta.persistence.*;

import java.util.Date;

@Entity // By annotating with entity I indicate to Spring that I will want to persist this class in a db
@Table(name = "Availability")
public class Availability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AVAILABILITY_ID")
    private int id;

    @Column(name = "PERSON_ID")
    private int person_id;

    //TODO set correct date format. how? static jakarta.persistence.TemporalType.DATE; maybe?
    private Date from_date;

    private Date to_date;


}
