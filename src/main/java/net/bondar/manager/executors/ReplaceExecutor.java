package net.bondar.manager.executors;

import net.bondar.manager.Command;
import net.bondar.manager.interfaces.IExecutor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;

/**
 * Provides executing replacing commands.
 */
public class ReplaceExecutor implements IExecutor {

    /**
     * Logger.
     */
    private final Logger log = LogManager.getLogger(getClass());

    /**
     * Executes replacing command.
     *
     * @param command current command
     * @return true if command successfully executed, otherwise false
     */
    @Override
    public boolean execute(final Command command) {
        return replace(new File(command.getParameters().get(0)), new File(command.getParameters().get(1)));
    }

    /**
     * Replaces source file into new directory or renames source file.
     *
     * @param source      source file
     * @param destination destination file/directory
     * @return true if file successfully replaced/renamed, otherwise false
     */
    private boolean replace(final File source, final File destination) {
        String path;
        log.info("Start replacing file " + source.getAbsolutePath());
        if (destination.isDirectory()) {
            log.info("Replacing file " + source.getAbsolutePath() + " into " + destination.getAbsolutePath());
            path = destination.getAbsolutePath() + "/" + source.getName();
        } else {
            log.info("Renaming file " + source.getAbsolutePath() + " to " + destination.getAbsolutePath());
            path = destination.getAbsolutePath();
        }
        boolean result = source.renameTo(new File(path));
        log.info("Start replacing file " + source.getAbsolutePath());
        return result;
    }
}
