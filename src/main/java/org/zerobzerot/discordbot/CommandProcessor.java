package org.zerobzerot.discordbot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import org.jetbrains.annotations.NotNull;
import org.tinylog.Logger;
import org.zerobzerot.discordbot.commands.ClearCommand;
import org.zerobzerot.discordbot.commands.KickCommand;
import org.zerobzerot.discordbot.commands.PingCommand;
import org.zerobzerot.discordbot.commands.SlashCommand;

import java.util.HashMap;
import java.util.stream.Collectors;

public class CommandProcessor extends ListenerAdapter {

    private final JDA jda;
    private final HashMap<String, SlashCommand> commands;

    public CommandProcessor(JDA jda) {
        this.jda = jda;
        this.commands = new HashMap<>();
        register(new PingCommand());
        register(new KickCommand());
        register(new ClearCommand());
        Logger.info("Global commands can take up to 1 hour to propagate to the clients \uD83D\uDE48");
    }

    private void register(SlashCommand command) {
        Logger.debug("Registering command: \"" + command.name + "\"");
        commands.put(command.name, command);
        for (Guild guild : jda.getGuilds()) {
            guild.upsertCommand(command).complete();
        }
    }

    @Override
    public void onSlashCommand(@NotNull SlashCommandEvent event) {
        SlashCommand command = commands.get(event.getName());
        if (command == null) {
            Logger.warn("Command " + event.getName() + " was invoked but was not registered!");
            event.reply("Command " + event.getName() + " not found!").queue();
        } else {
            Logger.info(
                "Executing command " +
                    "\"" + event.getName() + "\" (" + event.getCommandId() + ")" +
                    " in channel " +
                    "\"" + event.getChannel().getName() + "\" (" + event.getChannel().getId() + ")" +
                    " with options: " +
                    event.getOptions().stream().map(OptionMapping::toString).collect(Collectors.joining(", "))
            );
            command.run(event);
        }
    }

}
