package net.bondar.manager;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains user input commands.
 */
public enum Command {

    EMPTY("empty", false, "Indicates empty command."),
    EXIT("exit", false, "Closes application."),
    COPY("cp", true, new ArrayList<>(2), "Copies the contents of a specified file to another file or copy a specified file to another directory."),
    REPLACE_RENAME("mv", true, new ArrayList<>(2), "Replaces the specified file to another directory or renames the specified file."),
    REMOVE("rm", true, new ArrayList<>(1), "Removes the specified file.");

    /**
     * Creates <code>Command</code> instance.
     *
     * @param identifier  command identifier
     * @param parametric  parametric value
     * @param description command description
     */
    Command(String identifier, boolean parametric, List<String> parameters, String description) {
        this.identifier = identifier;
        this.parametric = parametric;
        this.parameters = parameters;
        this.description = description;
    }

    /**
     * Creates <code>Command</code> instance.
     *
     * @param identifier  command identifier
     * @param parametric  parametric value
     * @param description command description
     */
    Command(String identifier, boolean parametric, String description) {
        this.identifier = identifier;
        this.parametric = parametric;
        this.description = description;
    }

    /**
     * Gets command identifier.
     *
     * @return command identifier
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Gets parametric value
     *
     * @return parametric value
     */
    public boolean isParametric() {
        return parametric;
    }

    /**
     * Gets list of command parameters.
     *
     * @return list of parameters
     */
    public List<String> getParameters() {
        return parameters;
    }

    /**
     * Sets list of command parameters.
     *
     * @param parameters list of command parameters
     */
    public void setParameters(List<String> parameters) {
        this.parameters = parameters;
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
     * Command identifier.
     */
    private final String identifier;

    /**
     * Identifies contains parameters.
     */
    private final boolean parametric;

    /**
     * List of parameters.
     */
    private List<String> parameters = new ArrayList<>();

    /**
     * Command description.
     */
    private String description;

    @Override
    public String toString() {
        return "Command{" +
                "identifier='" + identifier + '\'' +
                ", parametric=" + parametric +
                ", parameters=" + parameters +
                ", description='" + description + '\'' +
                '}';
    }
}
