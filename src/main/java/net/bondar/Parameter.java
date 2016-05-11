package net.bondar;

/**
 * Contains user input parameters.
 */
public enum Parameter {

    PATH("-p", "", "Path to the file.");

    /**
     * Creates <code>Parameter</code> instance.
     *
     * @param identifier parameter identifier
     * @param value      parameter value
     * @param description parameter description
     */
    Parameter(String identifier, String value, String description) {
        this.identifier = identifier;
        this.value = value;
        this.description = description;
    }

    /**
     * Gets parameter identifier.
     *
     * @return parameter identifier
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Sets parameter identifier.
     *
     * @param identifier parameter identifier
     */
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    /**
     * Gets parameter value.
     *
     * @return parameter value
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets parameter value.
     *
     * @param value parameter value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets parameter description.
     *
     * @return parameter description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets parameter description.
     *
     * @param description parameter description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Parameter identifier.
     */
    private String identifier;

    /**
     * Parameter value.
     */
    private String value;

    /**
     * Parameter description.
     */
    private String description;

    @Override
    public String toString() {
        return "Parameter{" +
                "identifier='" + identifier + '\'' +
                ", value='" + value + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
