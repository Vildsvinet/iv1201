package se.kth.iv1201.example.domain;

/**
 * Thrown whenever an attempt is made to perform a transaction that is not
 * allowed by the bank's business rules.
 */
public class IllegalDatabaseAccessException extends Exception {
    private static final long serialVersionUID = 6355945960847848819L;

    /**
     * Creates a new instance with the specified message.
     *
     * @param msg A message explaining why the exception is thrown.
     */
    public IllegalDatabaseAccessException(String msg) {
        super(msg);
    }
}
