package net.bondar;

import net.bondar.exceptions.FileManagerException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Contains user input commands.
 */
public enum Command {

    COPY(new ArrayList<>(), "Copies the contents of a specified file to another file or copy a specified file to another directory."),
    REPLACE(new ArrayList<>(), "Moves the specified file to another directory."),
    RENAME(new ArrayList<>(), "Renames the specified file."),
    REMOVE(new ArrayList<>(), "Removes the specified file.");

    /**
     * Creates <code>Command</code> instance.
     *
     * @param parameters list of parameters
     */
    Command(List<Parameter> parameters, String description) {
        patterns.put(Parameter.PATH.name(), Pattern.compile("^[A-Za-z0-9./_]+"));
        this.parameters = parameters;
        this.description = description;
    }

    /**
     * Creates <code>Command</code> instance.
     */
    Command(String description) {
        this.description = description;
    }

    /**
     * Gets list of command parameters.
     *
     * @return list of parameters
     */
    public List<Parameter> getParameters() {
        return parameters;
    }

    /**
     * Sets list of command parameters.
     *
     * @param parameters list of command parameters
     */
    public void setParameters(List<Parameter> parameters) {
        if (verify(this, parameters)) {
            this.parameters = parameters;
        }
    }

    /**
     * Gets command description.
     *
     * @return command description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets command description.
     *
     * @param description command description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Verifies current command's parameters.
     *
     * @param command    current command
     * @param parameters setting parameters
     * @return true, if verify is successful, else - false
     * @throws FileManagerException if received incorrect parameters
     */
    private boolean verify(Command command, List<Parameter> parameters) {
        log.debug("Start verifying parameters. Command: " + command.name());
        for (Parameter parameter : parameters) {
            Matcher matcher = patterns.get(parameter.name()).matcher(parameter.getValue());
            if (!matcher.matches()) {
                log.error("Error while verifying parameter: " + parameter.getValue());
                throw new FileManagerException("Error while verifying parameter: " + parameter.getValue());
            }
        }
        log.debug("Finish verifying command's parameters. Command: " + command.name());
        return true;
    }

    /**
     * Logger.
     */
    private final Logger log = LogManager.getLogger(getClass());

    /**
     * List of parameters.
     */
    private List<Parameter> parameters = new ArrayList<>();

    /**
     * Command description.
     */
    private String description;

    /**
     * Map with patterns for verifying parameters.
     */
    private Map<String, Pattern> patterns = new HashMap<>();

    @Override
    public String toString() {
        return "Command{" +
                "parameters=" + parameters +
                ", description='" + description + '\'' +
                ", patterns=" + patterns +
                '}';
    }
}
