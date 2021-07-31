package org.zerobzerot.discordbot.scheduling;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.tinylog.Logger;
import org.zerobzerot.discordbot.util.JacksonMappings;

import java.io.IOException;
import java.nio.file.Path;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

public class Scheduler {

    @JsonIgnore
    private static final Path path = Path.of("tasks.yml");

    @JsonIgnore
    private boolean isStarted = false;

    @JsonProperty("tasks")
    private List<Task> tasks;

    private Scheduler() {
    }

    private Scheduler(List<Task> tasks) {
        this.tasks = tasks;
    }

    public static Scheduler load() {
        Scheduler scheduler = null;
        final var file = path.toFile();
        if (file.exists() && file.isFile()) {
            Logger.info("Loading scheduler tasks from: " + path.toAbsolutePath());
            try {
                scheduler = JacksonMappings.YAML.readValue(file, Scheduler.class);
            } catch (IOException | UnsupportedOperationException e) {
                Logger.warn("Unable to load tasks from " + path + " because: " + e.getMessage());
            }
        }
        if (scheduler == null) {
            scheduler = new Scheduler(new ArrayList<>());
        }
        Logger.debug("Starting with tasks: " + scheduler.tasks.stream().map(Task::toString).collect(Collectors.joining(", ")));
        return scheduler;
    }

    public void schedule(Task task) {
        tasks.add(task);
        save();
    }

    public void start() {
        if (isStarted) return;
        else isStarted = true;
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Instant now = Instant.now();
                for (Task task : tasks) {
                    if (now.isAfter(Instant.ofEpochSecond(task.epoch))) {
                        Logger.info("Executing task: " + task);
                        // TODO: task execution, if success set task done.
                        task.done = true;
                    }
                }
                if (tasks.removeIf(task -> task.done)) save();
            }
        }, 1000, 1000 * 60);
    }

    private void save() {
        try {
            JacksonMappings.YAML.writeValue(path.toFile(), this);
        } catch (IOException | SecurityException e) {
            Logger.warn("Unable to save tasks file to " + path + " because: " + e.getMessage());
        }
    }

}
