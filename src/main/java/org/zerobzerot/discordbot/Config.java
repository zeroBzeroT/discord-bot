package org.zerobzerot.discordbot;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Set;

/**
 * Config POJO
 */
public class Config {

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

    static Config load(Path path) {
        final Config config;
        try {
            config = new ObjectMapper(new YAMLFactory()).readValue(path.toFile(), Config.class);
        } catch (IOException | UnsupportedOperationException e) {
            throw new IllegalArgumentException("Unable to load config file at " + path + " because: " + e.getMessage());
        }
        return config;
    }

}
