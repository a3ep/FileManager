package net.bondar.manager.executors;

import net.bondar.manager.Command;
import net.bondar.manager.interfaces.IExecutor;
import net.bondar.manager.interfaces.IExecutorFactory;

/**
 * Creates file command executors.
 */
public class ExecutorFactory implements IExecutorFactory {

    /**
     * Creates file command executor.
     *
     * @param command current command
     * @return concrete file command executor
     */
    @Override
    public IExecutor createExecutor(final Command command) {
        if (command.equals(Command.COPY)) {
            return new CopyExecutor();
        } else if (command.equals(Command.REPLACE)) {
            return new ReplaceExecutor();
        } else {
            return new RemoveExecutor();
        }
    }
}
