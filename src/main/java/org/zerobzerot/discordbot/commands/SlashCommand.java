package org.zerobzerot.discordbot.commands;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import org.jetbrains.annotations.NotNull;
import org.tinylog.Logger;

public abstract class SlashCommand extends CommandData {

    public final @NotNull String name;
    public final @NotNull String description;

    public SlashCommand(final @NotNull String name, final @NotNull String description, Type type) {
        super(name, description);
        this.name = name;
        this.description = description;
        if (type == Type.ADMIN) {
            setDefaultEnabled(false);
        } else if (type == Type.MOD) {
            setDefaultEnabled(false);
        } else if (type == Type.PUBLIC) {
            setDefaultEnabled(true);
        }
    }

    public void run(SlashCommandEvent event) {
        Logger.warn("Command " + event.getName() + " was invoked but logic is not yet implemented!");
    }

    public enum Type {
        ADMIN,
        MOD,
        PUBLIC,
    }

}
