package net.bondar.parser;

import net.bondar.manager.Command;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.List;

/**
 * Provides parsing user input arguments.
 */
public class ParserService implements IParserService {

    /**
     * Logger.
     */
    private final Logger log = LogManager.getLogger(getClass());

    /**
     * Command finder.
     */
    private final CommandFinder commandFinder;

    /**
     * Creates <code>ParameterParser</code> instance.
     *
     * @param commandFinder command finder
     */
    public ParserService(CommandFinder commandFinder) {
        this.commandFinder = commandFinder;
    }

    /**
     * Parses user input arguments.
     *
     * @param args user input arguments
     * @return current command
     * @throws ParsingException if received incorrect arguments
     * @see {@link IParserService}
     */
    @Override
    public Command parse(String[] args) throws ParsingException {
        log.info("Start parsing input arguments: " + Arrays.toString(args));
        List<String> argsList = Arrays.asList(args);
        try {
            Command currentCommand = commandFinder.findCommand(argsList.get(0));
            if (currentCommand.isParametric()) {
                currentCommand.setParameters(argsList.subList(1, argsList.size()));
            }
            log.info("Finish parsing input arguments. Current command: " + currentCommand.name() + ", parameters: "
                    + currentCommand.getParameters());
            return currentCommand;
        } catch (ParsingException e) {
            log.error("Error while parsing input arguments: " + argsList.toString());
            throw new ParsingException("Error while parsing input arguments: " + argsList.toString(), e);
        }
    }
}
