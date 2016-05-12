package net.bondar.manager.exceptions;

/**
 * Custom exception for errors occurred while processing commands.
 */
public class ProcessingException extends RuntimeException {

    /**
     * Creates <code>ProcessingException</code> instance.
     */
    public ProcessingException() {
        super();
    }

    /**
     * Creates <code>ProcessingException</code> instance.
     *
     * @param message the detail error message
     */
    public ProcessingException(String message) {
        super(message);
    }

    /**
     * Creates <code>ProcessingException</code> instance.
     *
     * @param message the detail error message
     * @param cause   the cause of exception
     */
    public ProcessingException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Creates <code>ProcessingException</code> instance.
     *
     * @param cause the cause of exception
     */
    public ProcessingException(Throwable cause) {
        super(cause);
    }

    /**
     * Creates <code>ProcessingException</code> instance.
     *
     * @param message            the detail error message
     * @param cause              the cause of exception
     * @param enableSuppression  whether or not suppression is enabled or disabled
     * @param writableStackTrace whether or not the stack trace should be writable
     */

    protected ProcessingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
