package net.bondar.parser;

import net.bondar.manager.Command;
import net.bondar.parser.ParsingException;

/**
 * Provides parsing user input.
 */
public interface IParserService {

    /**
     * Parses user input arguments.
     *
     * @param args user input arguments
     * @return user input command
     * @throws ParsingException if received incorrect arguments
     */
    Command parse(final String[] args) throws ParsingException;
}
