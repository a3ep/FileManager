package net.bondar.manager.interfaces;

import java.io.File;

/**
 * Provides copying files.
 */
public interface IFileCopier {

    /**
     * Copies content of source file into destination file.
     *
     * @param sourceFile source file
     * @param destinationFile destination file
     * @return file containing the copied content
     */
    File copyFile(File sourceFile, File destinationFile);
}
