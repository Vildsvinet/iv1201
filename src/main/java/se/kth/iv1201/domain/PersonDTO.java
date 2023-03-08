package se.kth.iv1201.domain;

/**
 * Defines all operation that can be performed on an {@link Person} outside the
 * application and domain layers.
 */
public interface PersonDTO {
    /**
     * Returns the username.
     */
    String getUsername();

    /**
     * Returns the password.
     */
    String getPassword();

    /**
     * Returns the status of the person's possible application.
     */
    int getApplicationStatus();

    /**
     * Returns the first name of a person.
     */
    String getName();

    /**
     * Returns the last name of a person.
     */
    String getSurname();

    /**
     * Returns whether the user is admin, applicant, or recruiter.
     */
    int getRole_id();

}

