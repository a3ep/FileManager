package net.bondar.manager.exceptions;

/**
 * Custom exception for errors occurred while reading configurations.
 */
public class ConfigurationException extends RuntimeException {

    /**
     * Creates <code>ConfigurationException</code> instance.
     */
    public ConfigurationException() {
        super();
    }

    /**
     * Creates <code>ConfigurationException</code> instance.
     *
     * @param message the detail error message
     */
    public ConfigurationException(String message) {
        super(message);
    }

    /**
     * Creates <code>ConfigurationException</code> instance.
     *
     * @param message the detail error message
     * @param cause   the cause of exception
     */
    public ConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Creates <code>ConfigurationException</code> instance.
     *
     * @param cause the cause of exception
     */
    public ConfigurationException(Throwable cause) {
        super(cause);
    }

    /**
     * Creates <code>ConfigurationException</code> instance.
     *
     * @param message            the detail error message
     * @param cause              the cause of exception
     * @param enableSuppression  whether or not suppression is enabled or disabled
     * @param writableStackTrace whether or not the stack trace should be writable
     */

    protected ConfigurationException(String message, Throwable cause, boolean enableSuppression,
                                     boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
