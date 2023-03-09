package se.kth.iv1201.domain;

/**
 * Thrown whenever an attempt is made to perform a transaction that is not
 * allowed by the business rules of the recruitment application.
 * For example when we try to create a user with the same username as an existing user.
 */
public class IllegalDatabaseException extends Exception {
    /**
     * Creates a new instance with the specified message.
     *
     * @param msg A message explaining why the exception is thrown.
     */
    public IllegalDatabaseException(String msg) {
        super(msg);
    }
}
