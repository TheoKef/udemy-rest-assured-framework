package org.tutorials.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    private static volatile PropertyReader propertyReaderInstance;

    private PropertyReader() {
    }

    public static synchronized PropertyReader getInstance() {
        if(propertyReaderInstance == null) {
            propertyReaderInstance = new PropertyReader();
        }

        return propertyReaderInstance;
    }

    /**
     * Get property from properties file
     * @param propertyName Property's name
     * @return Property's value
     */
    public String getProperty(String propertyName) {

        Properties properties = new Properties();

        try {
            InputStream is = getClass()
                    .getClassLoader()
                    .getResourceAsStream("application.properties");

            properties.load(is);

            if(properties.getProperty(propertyName) != null) {
                return properties.getProperty(propertyName);
            }
        } catch (IOException io) {
            System.out.println(io.getMessage());
        }

        return null;
    }
}
