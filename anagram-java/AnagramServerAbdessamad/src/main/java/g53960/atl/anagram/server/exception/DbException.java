package g53960.atl.anagram.server.exception;

/**
 * 
 * @author jlc
 */
public class DbException extends Exception {

    /**
     * Creates a new instance of <code>DbException</code> without detail message.
     */
    public DbException() {
    }


    /**
     * Constructs an instance of <code>DbException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public DbException(String msg) {
        super(msg);
    }
}
