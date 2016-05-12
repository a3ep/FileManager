package net.bondar.manager;

import net.bondar.manager.exceptions.ConfigurationException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Holds application configurations and parameters.
 */
public class ConfigHolder {

    /**
     * Logger.
     */
    private final Logger log = LogManager.getLogger(getClass());

    /**
     * Application properties.
     */
    private Properties properties;

    /**
     * Creates <code>ConfigHolder</code> instance.
     *
     * @throws ConfigurationException when occurred exception during loading properties
     */
    public ConfigHolder() throws ConfigurationException {
        properties = new Properties();
        String propertiesFile = "config.properties";
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propertiesFile);
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                log.error("Property file " + propertiesFile + " not found in the classpath)");
                throw new ConfigurationException("Property file " + propertiesFile + " not found in the classpath)");
            }
        } catch (IOException e) {
            log.warn("Error during loading properties. Message " + e.getMessage());
            throw new ConfigurationException("Error during loading properties. Exception:" + e.getMessage());
        }
    }

    /**
     * Gets the value of the specified parameter.
     *
     * @param key key of the specified parameter
     * @return parameter value
     */
    public String getValue(final String key) {
        return properties.getProperty(key);
    }
}
