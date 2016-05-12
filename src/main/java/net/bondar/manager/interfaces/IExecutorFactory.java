package net.bondar.manager.interfaces;

import net.bondar.manager.Command;

/**
 * Creates command executors.
 */
public interface IExecutorFactory {

    /**
     * Creates command executor.
     *
     * @param command current command
     * @return concrete command executor
     */
    IExecutor createExecutor(final Command command);
}
