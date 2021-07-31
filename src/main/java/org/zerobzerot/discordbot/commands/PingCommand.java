package org.zerobzerot.discordbot.commands;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import org.zerobzerot.discordbot.Emojis;

public class PingCommand extends SlashCommand {

    public PingCommand() {
        super("ping", "play ping pong", Type.PUBLIC);
        addOption(OptionType.USER, "user", "user to ping", false);
    }

    @Override
    public void run(SlashCommandEvent event) {
        var userOption = event.getOption("user");
        // Check if option is present
        if (userOption == null || userOption.getAsMember() == null) {
            // Send reply without name
            event.reply(Emojis.PING_PONG.toString()).setEphemeral(true).queue();
            return;
        }
        // Send reply with name
        event.reply(Emojis.WAVE + " " + userOption.getAsMember().getAsMention()).setEphemeral(true).queue();
    }

}
