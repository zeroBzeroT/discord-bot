package org.zerobzerot.discordbot.commands;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import org.jetbrains.annotations.NotNull;
import org.tinylog.Logger;
import org.zerobzerot.discordbot.config.Config;

import java.util.stream.Collectors;

public abstract class SlashCommand extends CommandData {

    public final @NotNull String name;
    public final @NotNull String description;

    public SlashCommand(final @NotNull String name, final @NotNull String description, Type type) {
        super(name, description);
        this.name = name;
        this.description = description;
        if (type == Type.ADMIN) {
            setDefaultEnabled(true);
            // TODO: set privileges for admin roles
            Logger.debug(Config.getInstance().adminRoleIds.stream().map(String::valueOf).collect(Collectors.joining(", ")));
        } else if (type == Type.MOD) {
            setDefaultEnabled(true);
            // TODO: set privileges for mod and admin roles
            Logger.debug(Config.getInstance().modRoleIds.stream().map(String::valueOf).collect(Collectors.joining(", ")));
        } else if (type == Type.PUBLIC) {
            setDefaultEnabled(true);
        }
    }

    public void run(SlashCommandEvent event) {
        Logger.warn("Command " + event.getName() + " was invoked but logic is not yet implemented!");
        event.reply("Command " + event.getName() + " not yet implemented!").queue();
    }

    public enum Type {
        ADMIN,
        MOD,
        PUBLIC,
    }

}
