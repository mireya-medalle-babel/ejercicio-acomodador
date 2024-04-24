package com.babel.ejercicioacomodador.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "com.babel.ejercicioacomodador")
public class Config {
    private static final String CONFIGURATION_FILE = "application.properties";
    private static final int DEFAULT_ROWS = 9;
    private static final int DEFAULT_COLS = 10;
    private static final String ROWS_KEY = "rows";
    private static final String COLS_KEY = "cols";
    private Properties configuration;

    private void loadConfig() {
        configuration = new Properties();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(CONFIGURATION_FILE);

        try (InputStreamReader configReader = new InputStreamReader(inputStream)) {
            configuration.load(configReader);
        } catch(IOException e) {
            //
        }
    }

    public Config() {
        loadConfig();
    }

    public int getDefaultRows() {
        try {
            return Integer.parseInt(configuration.getProperty(ROWS_KEY));
        } catch (Exception e) {
            return DEFAULT_ROWS;
        }
    }

    public int getDefaultCols() {
        try {
            return Integer.parseInt(configuration.getProperty(COLS_KEY));
        } catch (Exception e) {
            return DEFAULT_COLS;
        }
    }

}
