package org.zerobzerot.discordbot.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Config POJO
 */
public class Config {

    private static final ReentrantLock lock = new ReentrantLock();
    private static final Path path = Path.of("config.yml");

    private static Config instance;

    @JsonProperty("token")
    public String token;

    @JsonProperty("roles-mods")
    public Set<Long> modRoleIds;

    @JsonProperty("roles-admins")
    public Set<Long> adminRoleIds;

    @JsonProperty("role-muted")
    public long mutedRoleId;

    @JsonProperty("channel-logs")
    public long logChannelId;

    @JsonProperty("redis-host")
    public String redisHost;

    @JsonProperty("redis-port")
    public int redisPort;

    private Config() {
    }

    public static Config getInstance() {
        lock.lock();

        if (instance != null) {
            lock.unlock();
            return instance;
        }

        var file = path.toFile();
        if (file.isDirectory()) throw new IllegalStateException(path + " is a directory!");
        if (!file.exists()) {
            var resource = ClassLoader.getSystemResourceAsStream(path.getFileName().toString());
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

        instance = config;
        lock.unlock();
        return instance;
    }

}
