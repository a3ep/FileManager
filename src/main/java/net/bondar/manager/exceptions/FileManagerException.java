package net.bondar.manager.exceptions;

/**
 * Custom application exception.
 */
public class FileManagerException extends RuntimeException{

    /**
     * Creates <code>FileManagerException</code> instance.
     */
    public FileManagerException() {
        super();
    }

    /**
     * Creates <code>FileManagerException</code> instance.
     *
     * @param message the detail error message
     */
    public FileManagerException(String message) {
        super(message);
    }

    /**
     * Creates <code>FileManagerException</code> instance.
     *
     * @param message the detail error message
     * @param cause   the cause of exception
     */
    public FileManagerException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Creates <code>FileManagerException</code> instance.
     *
     * @param cause the cause of exception
     */
    public FileManagerException(Throwable cause) {
        super(cause);
    }
    /**
     * Creates <code>FileManagerException</code> instance.
     *
     * @param message            the detail error message
     * @param cause              the cause of exception
     * @param enableSuppression  whether or not suppression is enabled or disabled
     * @param writableStackTrace whether or not the stack trace should be writable
     */

    protected FileManagerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
