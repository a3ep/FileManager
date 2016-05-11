package net.bondar.parser;

import net.bondar.manager.Command;
import net.bondar.parser.ParsingException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Arrays;

/**
 * Provides finding commands.
 */
public class CommandFinder {

    /**
     * Logger.
     */
    private final Logger log = LogManager.getLogger(getClass());

    /**
     * Finds command in the specified array of strings.
     *
     * @param commandString first string of input
     * @return current command
     * @throws ParsingException if command is not found
     */
    public Command findCommand(final String commandString) throws ParsingException {
        log.debug("Finding current command.");
        for (Command command : Arrays.asList(Command.values())) {
            if (commandString.equals(command.getIdentifier())) {
                log.debug("Current command: " + command.name());
                return command;
            }
        }
        log.error("Error while finding command.");
        throw new ParsingException("Error while finding command in " + commandString);
    }
}
