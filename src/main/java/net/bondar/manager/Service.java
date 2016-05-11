package net.bondar.manager;

import net.bondar.manager.interfaces.IFileCopier;
import net.bondar.manager.interfaces.IService;
import net.bondar.manager.processors.CopyOperationProcessor;
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
public class Service implements IService {

    /**
     * Logger.
     */
    private final Logger log = LogManager.getLogger(getClass());

    /**
     * Parser service.
     */
    private final IParserService parserService;

    /**
     * File copier.
     */
    private final IFileCopier fileCopier;

    /**
     * Input command.
     */
    private Command inputCommand = Command.EMPTY;

    /**
     * Creates <code>Service</code> instance.
     *
     * @param parserService parser service.
     */
    public Service(IParserService parserService, IFileCopier fileCopier) {
        this.parserService = parserService;
        this.fileCopier = fileCopier;
    }

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
                        log.debug("Start operation -> " + inputCommand.name() + ", " + inputCommand.getParameters().toString());
                        new CopyOperationProcessor(inputCommand.getParameters().get(0), inputCommand.getParameters().get(1), fileCopier).process();
                        log.debug("Finish operation -> " + inputCommand.name() + "\n");
                        break;
                    case REPLACE_RENAME:
                        log.debug("Start operation -> " + inputCommand.name() + ", " + inputCommand.getParameters().toString());
                        log.debug("Finish operation -> " + inputCommand.name() + "\n");
                        break;
                    case REMOVE:
                        break;
                }
            } catch (ParsingException e) {
                log.error("File Splitter Application error. Message: " + e.getMessage() + "\n");
            } catch (IOException e) {
                log.error("Error while processing user input. Message " + e.getMessage());
            }
        }
    }
}
