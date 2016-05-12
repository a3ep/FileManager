package net.bondar.manager;

import net.bondar.manager.exceptions.ExecutingException;
import net.bondar.manager.exceptions.ProcessingException;
import net.bondar.manager.interfaces.IExecutor;
import net.bondar.manager.interfaces.IExecutorFactory;
import net.bondar.manager.interfaces.IProcessor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Provides processing user commands.
 */
public class FileManagerProcessor implements IProcessor {

    /**
     * Logger.
     */
    private final Logger log = LogManager.getLogger(getClass());

    /**
     * Current command.
     */
    private final Command command;

    /**
     * Command executor.
     */
    private final IExecutor executor;

    /**
     * Creates <code>FileManagerProcessor</code> instance.
     *
     * @param command current command
     */
    public FileManagerProcessor(final Command command, final IExecutorFactory executorFactory) {
        this.command = command;
        this.executor = executorFactory.createExecutor(command);
    }

    /**
     * Process current command.
     *
     * @return true if command processed successful, otherwise false
     * @throws ProcessingException if a <code>{@link ExecutingException}</code> occurring while executing command
     */
    @Override
    public boolean process() {
        log.info("Start processing command " + command.name() + " with parameters: " + command.getParameters());
        try {
            log.debug("Executing command " + command.name());
            boolean result = executor.execute(command);
            log.info("Finish processing command " + command.name() + ".\n");
            return result;
        } catch (ExecutingException e) {
            log.error("Error while processing command " + command.name() + ". Current parameters: "
                    + command.getParameters() + ". Message: " + e.getMessage());
            throw new ProcessingException("Error while processing command " + command.name() + ". Current parameters: "
                    + command.getParameters() + ".", e);
        }
    }
}
