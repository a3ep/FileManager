package net.bondar.manager.processors;

import net.bondar.manager.exceptions.FileManagerException;
import net.bondar.manager.interfaces.IFileCopier;
import net.bondar.manager.interfaces.IOperationProcessor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;

/**
 * Provides processing copy operations.
 */
public class CopyOperationProcessor implements IOperationProcessor {

    /**
     * Logger.
     */
    private final Logger log = LogManager.getLogger(getClass());

    /**
     * Source file destination.
     */
    private final String sourceDestination;

    /**
     * Output file or directory destination.
     */
    private final String outputDestination;

    /**
     * File copier.
     */
    private final IFileCopier fileCopier;

    /**
     * Creates <code>CopyOperationProcessor</code> instance.
     *
     * @param sourceDestination source file destination
     * @param outputDestination output file or directory destination
     * @param fileCopier file copier
     */
    public CopyOperationProcessor(String sourceDestination, String outputDestination, IFileCopier fileCopier) {
        this.sourceDestination = sourceDestination;
        this.outputDestination = outputDestination;
        this.fileCopier = fileCopier;
    }

    /**
     * Process copy operation.
     *
     * @return true if operation processed successful, otherwise false
     */
    @Override
    public boolean process() {
        log.info("Start processing.");
        try {
            File sourceFile = new File(sourceDestination);
            File destinationFile = new File(outputDestination);
            if (destinationFile.createNewFile() && destinationFile.isFile()) {
                fileCopier.copyFile(sourceFile, destinationFile);
            } else if (destinationFile.isDirectory()) {

            }
            log.info("Finish processing.");
            return true;
        }catch (IOException e){
            log.error("Error while processing operation. Current parameters: " + sourceDestination + ", "
                    + outputDestination + ". Message: " + e.getMessage());
            throw new FileManagerException("Error while processing operation. Current parameters: "
                    + sourceDestination + ", " + outputDestination + ".", e);
        }
    }
}
