package net.bondar.manager;

import net.bondar.manager.executors.ExecutorFactory;
import net.bondar.manager.interfaces.IExecutorFactory;
import net.bondar.parser.CommandFinder;
import net.bondar.parser.IParserService;
import net.bondar.parser.ParserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Arrays;

/**
 * The application starting point.
 */
public class Main {

    /**
     * Logger.
     */
    private static final Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            CommandFinder commandFinder = new CommandFinder();
            IParserService parserService = new ParserService(commandFinder);
            IExecutorFactory executorFactory = new ExecutorFactory();
            new FileManagerService(parserService, executorFactory).run();
        } catch (Throwable t) {
            log.fatal("Unexpected application error. Message: " + Arrays.toString(t.getStackTrace()));
        }
    }
}
