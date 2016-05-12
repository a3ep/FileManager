package net.bondar.manager.executors;

import net.bondar.manager.Command;
import net.bondar.manager.ConfigHolder;
import net.bondar.manager.exceptions.ExecutingException;
import net.bondar.manager.interfaces.IExecutor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Provides executing copy commands.
 */
public class CopyExecutor implements IExecutor {

    /**
     * Logger.
     */
    private final Logger log = LogManager.getLogger(getClass());

    /**
     * Config holder.
     */
    private final ConfigHolder configHolder = new ConfigHolder();

    /**
     * Executes copy command.
     *
     * @param command current command
     * @return true if command successfully executed, otherwise false
     * @throws ExecutingException if errors occurring while executing command
     */
    @Override
    public boolean execute(Command command) throws ExecutingException {
        return copy(new File(command.getParameters().get(0)), new File(command.getParameters().get(1)));
    }

    /**
     * Copies source file content into destination file or copies source file into destination directory.
     *
     * @param source      source file
     * @param destination destination file/directory
     * @return true if the specified file successfully copied, otherwise false
     * @throws ExecutingException if errors occurring while copying file
     */
    private boolean copy(File source, File destination) throws ExecutingException {
        return destination.isDirectory() ? copyFileToDirectory(source, destination)
                : copyFileContent(source, destination);
    }

    /**
     * Copies content of source file into destination file.
     *
     * @param sourceFile      source file
     * @param destinationFile destination file
     * @return true if file copied successful, otherwise throws <code>{@link ExecutingException}</code>
     * @throws ExecutingException if <code>IOException</code> occurring while copying file
     */
    private boolean copyFileContent(File sourceFile, File destinationFile) throws ExecutingException {
        long finish = sourceFile.length();
        log.info("Start copying content of file " + sourceFile.getAbsolutePath() + " into file "
                + destinationFile.getAbsolutePath());
        try (RandomAccessFile source = new RandomAccessFile(sourceFile, "r");
             RandomAccessFile output = new RandomAccessFile(destinationFile, "rw")) {
            readWrite(source, output, finish);
            log.info("Finish copying content of file " + sourceFile.getAbsolutePath() + " into file "
                    + destinationFile.getAbsolutePath());
        } catch (IOException e) {
            log.error("Error during copying file " + sourceFile.getAbsolutePath() + ". Message " + e.getMessage());
            throw new ExecutingException("Error during copying file " + sourceFile.getAbsolutePath() + ".", e);
        }
        return true;
    }

    /**
     * Copies the specified file into the specified directory.
     *
     * @param sourceFile     source file
     * @param destinationDir destination directory
     * @return true if the specified file copied successful, false if file already exist
     * @throws ExecutingException if path to destination file is invalid
     */
    private boolean copyFileToDirectory(File sourceFile, File destinationDir) throws ExecutingException {
        log.info("Start copying file " + sourceFile.getAbsolutePath() + " into directory "
                + destinationDir.getAbsolutePath());
        boolean result = false;
        File destinationFile = new File(destinationDir.getAbsolutePath() + "/" + sourceFile.getName());
        try {
            if (destinationFile.createNewFile()) {
                result = copyFileContent(sourceFile, destinationFile);
                log.info("Finish copying file " + sourceFile.getAbsolutePath() + " into directory "
                        + destinationDir.getAbsolutePath());
            }
            log.warn("Warning! File " + sourceFile.getAbsolutePath() + " already exist! Please check your input.");
            return result;
        } catch (IOException e) {
            log.error("Error while creating file " + destinationFile.getAbsolutePath() + ". Message " + e.getMessage());
            throw new ExecutingException("Error during copying file " + sourceFile.getAbsolutePath() + ".", e);
        }
    }

    /**
     * Reads content of source file and write this content into output file.
     *
     * @param source source file
     * @param output output file
     * @param finish source file length, finish position index
     * @throws IOException if error occurring while read/write bytes
     */
    private void readWrite(RandomAccessFile source, RandomAccessFile output, final double finish) throws IOException {
        double start = 0;
        while (start < finish) {
            byte[] array = new byte[getAvailableSize(finish, start, Integer.parseInt(configHolder.getValue("bufferSize")))];
            log.debug("Copying process: " + (int) ((start / finish) * 100) + "%, buffer = " + array.length);
            source.read(array);
            output.write(array);
            start += array.length;
        }
    }

    /**
     * Gets the required size of the byte array to write into the file.
     *
     * @param finish     index of the end position in a file.
     * @param start      index of the start position in a file.
     * @param bufferSize default buffer size
     * @return required byte array size
     */
    private int getAvailableSize(final double finish, final double start, final int bufferSize) {
        return ((finish - start) >= bufferSize) ? bufferSize : (int) (finish - start);
    }
}
