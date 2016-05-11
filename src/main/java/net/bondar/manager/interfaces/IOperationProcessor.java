package net.bondar.manager.interfaces;

/**
 * Provides file operation processing.
 */
public interface IOperationProcessor {

    /**
     * Processes file operation.
     *
     * @return true if process ok, otherwise false
     */
    boolean process();
}
