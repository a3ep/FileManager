package net.bondar.manager;

import net.bondar.manager.exceptions.ProcessingException;
import net.bondar.manager.interfaces.IExecutorFactory;
import net.bondar.manager.interfaces.IService;
import net.bondar.parser.IParserService;
import net.bondar.parser.ParsingException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Provides communication with user.
 */
public class FileManagerService implements IService {

    /**
     * Logger.
     */
    private final Logger log = LogManager.getLogger(getClass());

    /**
     * Parser service.
     */
    private final IParserService parserService;

    /**
     * Executor factory.
     */
    private final IExecutorFactory executorFactory;

    /**
     * Input command.
     */
    private Command inputCommand = Command.EMPTY;

    /**
     * Creates <code>FileManagerService</code> instance.
     *
     * @param parserService   parser service.
     * @param executorFactory executor factory
     */
    public FileManagerService(final IParserService parserService, final IExecutorFactory executorFactory) {
        this.parserService = parserService;
        this.executorFactory = executorFactory;
    }

    /**
     * Runs communication with user.
     */
    @Override
    public void run() {
        log.info("Start application.");
        String input;
        while (!inputCommand.equals(Command.EXIT)) {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                input = br.readLine();
                log.debug("Introduced string -> " + input);
                inputCommand = parserService.parse(input.split(" "));
                switch (inputCommand) {
                    case EXIT:
                        log.debug("Closing resources...");
                        br.close();
                        log.debug("Application closed.");
                        break;
                    case COPY:
                        if (!new FileManagerProcessor(inputCommand, executorFactory).process()) {
                            inputCommand = Command.EXIT;
                            break;
                        }
                        break;
                    case REPLACE:
                        if (!new FileManagerProcessor(inputCommand, executorFactory).process()) {
                            inputCommand = Command.EXIT;
                            break;
                        }
                        break;
                    case REMOVE:
                        if (!new FileManagerProcessor(inputCommand, executorFactory).process()) {
                            inputCommand = Command.EXIT;
                            break;
                        }
                        break;
                }
            } catch (ProcessingException | ParsingException e) {
                log.error("File Manager Application error. Message: " + e.getMessage() + "\n");
            } catch (IOException e) {
                log.error("Error while processing user input. Message " + e.getMessage());
            }
        }
    }
}
