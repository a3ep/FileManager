package net.bondar.manager.executors;

import net.bondar.manager.Command;
import net.bondar.manager.exceptions.ExecutingException;
import net.bondar.manager.interfaces.IExecutor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;

/**
 * Provides executing removing commands.
 */
public class RemoveExecutor implements IExecutor {

    /**
     * Logger.
     */
    private final Logger log = LogManager.getLogger(getClass());


    /**
     * Executes removing command.
     *
     * @param command current command
     * @return true if command successfully executed, otherwise false
     * @throws ExecutingException if errors occurring while executing command
     */
    @Override
    public boolean execute(Command command) throws ExecutingException {
        return remove(new File(command.getParameters().get(0)));
    }

    /**
     * Removes the specified file/directory.
     *
     * @param file specified file/directory
     * @return true if file/directory successfully removed, otherwise false
     * @throws ExecutingException if error occurring while removing file or directory
     */
    private boolean remove(File file) throws ExecutingException {
        return file.isDirectory() ? removeDirectory(file) : removeFile(file);
    }

    /**
     * Removes the specified file.
     *
     * @param file specified file
     * @return true if file successfully removed, otherwise false
     * @throws ExecutingException if a security manager exists and its
     * <code>{@link java.lang.SecurityManager#checkRead(java.lang.String)}</code> method denies read access to the file
     */
    private boolean removeFile(File file) throws ExecutingException {
        boolean result;
        log.info("Start removing file " + file.getAbsolutePath());
        try {
            result = file.delete();
        } catch (SecurityException e) {
            log.error("Error during removing file " + file.getAbsolutePath() + ". Message " + e.getMessage());
            throw new ExecutingException("Error during removing file " + file.getAbsolutePath() + ".", e);
        }
        log.info("Finish removing file " + file.getAbsolutePath());
        return result;
    }

    /**
     * Removes the specified directory.
     *
     * @param directory specified directory
     * @return true if directory successfully removed, otherwise false
     * @throws ExecutingException if errors occurring while invoke <code>removeFile(File file)</code> method
     */
    private boolean removeDirectory(File directory) throws ExecutingException {
        boolean result;
        log.info("Start removing directory " + directory.getAbsolutePath());
        if (directory.list().length == 0) {
            result = removeFile(directory);
        } else {
            for (String tmp : directory.list()) {
                File tmpFile = new File(directory, tmp);
                remove(tmpFile);
            }
            result = removeFile(directory);
        }
        return result;
    }
}
