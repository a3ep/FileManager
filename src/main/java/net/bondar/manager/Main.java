package net.bondar.manager;

import net.bondar.manager.interfaces.IFileCopier;
import net.bondar.manager.utils.FileCopier;
import net.bondar.parser.CommandFinder;
import net.bondar.parser.IParserService;
import net.bondar.parser.ParserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

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
            IFileCopier fileCopier = new FileCopier();
            new Service(parserService, fileCopier).run();
        }catch (Throwable t){
            log.fatal("Unexpected application error. Message: " + t.getMessage());
        }
    }
}
