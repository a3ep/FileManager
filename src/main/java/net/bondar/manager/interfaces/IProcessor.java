package net.bondar.manager.interfaces;

/**
 * Provides file command processing.
 */
public interface IProcessor {

    /**
     * Processes file command.
     *
     * @return true if command processed successful, otherwise false
     */
    boolean process();
}
