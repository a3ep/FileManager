package net.bondar.manager.exceptions;

/**
 * Custom exception for errors occurred while executing commands.
 */
public class ExecutingException extends RuntimeException {

    /**
     * Creates <code>ExecutingException</code> instance.
     */
    public ExecutingException() {
        super();
    }

    /**
     * Creates <code>ExecutingException</code> instance.
     *
     * @param message the detail error message
     */
    public ExecutingException(String message) {
        super(message);
    }

    /**
     * Creates <code>ExecutingException</code> instance.
     *
     * @param message the detail error message
     * @param cause   the cause of exception
     */
    public ExecutingException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Creates <code>ExecutingException</code> instance.
     *
     * @param cause the cause of exception
     */
    public ExecutingException(Throwable cause) {
        super(cause);
    }

    /**
     * Creates <code>ExecutingException</code> instance.
     *
     * @param message            the detail error message
     * @param cause              the cause of exception
     * @param enableSuppression  whether or not suppression is enabled or disabled
     * @param writableStackTrace whether or not the stack trace should be writable
     */

    protected ExecutingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
