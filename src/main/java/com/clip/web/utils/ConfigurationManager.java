package com.clip.web.utils;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.ConfigurationFactory;

import java.net.URL;

public class ConfigurationManager {
    private static Configuration config = null;
    private static URL CONFIGURATION_FILE_URL = ConfigurationManager.class.getResource("/configuration-list.xml");

    private static final Object lock = new Object();

    private ConfigurationManager() {
    }

    public static Configuration getConfig() {
        if (config == null) {
            synchronized (lock) {
                ConfigurationFactory factory = new ConfigurationFactory();
                factory.setConfigurationURL(CONFIGURATION_FILE_URL);
                try {
                    config = factory.getConfiguration();
                } catch (ConfigurationException e) {
                    e.printStackTrace();
                }
            }
        }
        return config;
    }
}
