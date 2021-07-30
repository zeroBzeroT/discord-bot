package org.zerobzerot.discordbot;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;

/**
 * Config POJO
 */
public class Config {

    private static final Path path = Path.of("config.yml");

    @JsonProperty("token")
    String token;

    @JsonProperty("roles-admins")
    Set<Long> adminRoleIds;

    @JsonProperty("roles-mods")
    Set<Long> modRoleIds;

    @JsonProperty("channel-logs")
    long logChannelId;

    private Config() {
    }

    static Config load() {
        File file = path.toFile();
        if (file.isDirectory()) throw new IllegalStateException(path + " is a directory!");
        if (!file.exists()) {
            InputStream resource = ClassLoader.getSystemResourceAsStream(path.getFileName().toString());
            if (resource == null) throw new IllegalStateException("Default config not found in resources!");
            try {
                Files.copy(resource, path);
            } catch (IOException ex) {
                throw new IllegalStateException("Error while saving default config: " + ex.getMessage());
            }
        }

        final Config config;
        try {
            config = new ObjectMapper(new YAMLFactory()).readValue(path.toFile(), Config.class);
        } catch (IOException | UnsupportedOperationException e) {
            throw new IllegalArgumentException("Unable to load config file at " + path + " because: " + e.getMessage());
        }
        return config;
    }

}
