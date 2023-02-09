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
}

