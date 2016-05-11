package net.bondar.manager.utils;

import net.bondar.manager.exceptions.FileManagerException;
import net.bondar.manager.interfaces.IFileCopier;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Provides copying files.
 */
public class FileCopier implements IFileCopier {

    /**
     * Logger.
     */
    private final Logger log = LogManager.getLogger(getClass());

    /**
     * Copies content of source file into destination file.
     *
     * @param sourceFile      source file
     * @param destinationFile destination file
     * @return file containing the copied content
     */
    @Override
    public File copyFile(File sourceFile, File destinationFile) {
        long finish = sourceFile.length();
        log.info("Start copying content of file " + sourceFile.getName() + " into file " + destinationFile.getName());
        try (RandomAccessFile source = new RandomAccessFile(sourceFile, "r");
             RandomAccessFile output = new RandomAccessFile(destinationFile, "rw")) {
            readWrite(source, output, finish);
            log.info("Finish copying content of file " + sourceFile.getName() + " into file " + destinationFile.getName());
        } catch (IOException e) {
            log.error("Error during copying file " + sourceFile.getName() + ". Message " + e.getMessage());
            throw new FileManagerException("Error during copying file " + sourceFile.getName() + ".", e);
        }
        return destinationFile;
    }

    /**
     * @param source
     * @param output
     * @param finish
     * @throws IOException
     */
    private void readWrite(RandomAccessFile source, RandomAccessFile output, final long finish) throws IOException {
        long start = 0;
        while (start < finish) {
            byte[] array = new byte[getAvailableSize(finish, start, 1048576)];
            log.debug("Copying: start position = " + start + ", finish position = " + finish + ", buffer = " + array.length);
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
    private int getAvailableSize(final long finish, final long start, final int bufferSize) {
        return ((finish - start) >= bufferSize) ? bufferSize : (int) (finish - start);
    }
}
