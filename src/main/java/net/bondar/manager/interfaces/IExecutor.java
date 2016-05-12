package net.bondar.manager.interfaces;

import net.bondar.manager.Command;
import net.bondar.manager.exceptions.ExecutingException;

/**
 * Provides executing commands.
 */
public interface IExecutor {

    /**
     * Executes current command.
     *
     * @param command current command
     * @return true if command executed successful, otherwise false
     * @throws ExecutingException if errors occurring while executing command
     */
    boolean execute(final Command command) throws ExecutingException;
}
