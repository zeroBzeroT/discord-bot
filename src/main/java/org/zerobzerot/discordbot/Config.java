package org.zerobzerot.discordbot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Config POJO
 */
public class Config {

    private String token;

    private Config() {
    }

    public static Config load(Path path) {
        final Config config;
        try {
            config = new ObjectMapper(new YAMLFactory()).readValue(path.toFile(), Config.class);
        } catch (IOException | UnsupportedOperationException e) {
            throw new IllegalArgumentException("Unable to load config file at " + path + " because: " + e.getMessage());
        }
        return config;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
