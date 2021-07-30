package org.zerobzerot.discordbot.commands;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import org.jetbrains.annotations.NotNull;
import org.tinylog.Logger;

public abstract class SlashCommand extends CommandData {

    public final @NotNull String name;
    public final @NotNull String description;

    public SlashCommand(final @NotNull String name, final @NotNull String description) {
        super(name, description);
        this.name = name;
        this.description = description;
    }

    public void run(SlashCommandEvent event) {
        Logger.warn("Command " + event.getName() + " was invoked but logic is not yet implemented!");
    }

}
