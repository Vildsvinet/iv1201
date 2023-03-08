package se.kth.iv1201.domain;

import jakarta.persistence.*;

import java.util.Date;

/**
 * Handles availability for the applicants.
 */
@Entity //Annotating with entity indicates to Spring that this class should persist in a db
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
