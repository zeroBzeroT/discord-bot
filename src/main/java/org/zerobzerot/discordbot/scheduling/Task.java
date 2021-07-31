package org.zerobzerot.discordbot.scheduling;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

public class Task {

    /**
     * type of action to execute
     */
    @JsonProperty("type")
    public Type type;

    /**
     * snowflake id of target user
     */
    @JsonProperty("target")
    public long target;

    /**
     * epoch time of earliest allowed execution
     */
    @JsonProperty("epoch")
    public long epoch;

    @JsonIgnore
    boolean done;

    private Task() {
    }

    public Task(final @NotNull Type type, final long target, final long epoch) {
        this.type = type;
        this.target = target;
        this.epoch = epoch;
        this.done = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (target != task.target) return false;
        if (epoch != task.epoch) return false;
        return type == task.type;
    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + (int) (target ^ (target >>> 32));
        result = 31 * result + (int) (epoch ^ (epoch >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Task{" +
            "type=" + type +
            ", target=" + target +
            ", epoch=" + epoch +
            ", done=" + done +
            '}';
    }

    public enum Type {
        UNBAN,
        UNMUTE
    }

}
