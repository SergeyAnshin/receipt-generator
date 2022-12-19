package org.example.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyValueExtractor {
    public static final String PROPERTIES_FILE_NAME = "src/main/resources/application.properties";
    private Properties properties = new Properties();

    public String getPropertyValue(String propertyKey) {
        if (properties.isEmpty()) {
            loadProperties();
        }
        return properties.getProperty(propertyKey);
    }

    private void loadProperties() {
        try (InputStream inputStream = new FileInputStream(PROPERTIES_FILE_NAME)) {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
