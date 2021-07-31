package org.zerobzerot.discordbot;

import org.tinylog.Logger;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class Redis {

    private static Optional<String> read(String key) {
        final var config = Config.getInstance();
        final HostAndPort host = new HostAndPort(config.redisHost, config.redisPort);
        final Jedis jedis;
        final String value;
        try {
            jedis = new Jedis(host);
            value = jedis.get(key);
        } catch (JedisConnectionException e) {
            Logger.warn("Redis not reachable at " + host.getHost() + ":" + host.getPort() + " with exception: " + e.getMessage());
            return Optional.empty();
        }
        jedis.close();
        return Optional.of(value);
    }

    public static CompletableFuture<Optional<String>> readAsync(String key) {
        return CompletableFuture.supplyAsync(() -> read(key));
    }

}
