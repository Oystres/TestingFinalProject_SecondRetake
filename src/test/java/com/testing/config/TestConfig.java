package com.testing.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class TestConfig {
    private static final Properties PROPERTIES = loadProperties();

    private TestConfig() {
    }

    private static Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream inputStream = TestConfig.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (inputStream == null) {
                throw new IllegalStateException("config.properties file was not found");
            }
            properties.load(inputStream);
        } catch (IOException e) {
            throw new IllegalStateException("Unable to read config.properties", e);
        }
        return properties;
    }

    public static String get(String key) {
        return System.getProperty(key, PROPERTIES.getProperty(key));
    }

    public static int getInt(String key) {
        return Integer.parseInt(get(key));
    }

    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(get(key));
    }
}
