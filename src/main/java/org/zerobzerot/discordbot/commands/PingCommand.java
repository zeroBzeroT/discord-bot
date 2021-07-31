package org.zerobzerot.discordbot.commands;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;

public class PingCommand extends SlashCommand {

    public PingCommand() {
        super("ping", "play ping pong", Type.PUBLIC);
        addOption(OptionType.USER, "user", "user to ping", false);
    }

    @Override
    public void run(SlashCommandEvent event) {
        OptionMapping user = event.getOption("user");
        // Check if option is present
        if (user == null || user.getAsMember() == null) {
            // Send reply without name
            event.reply("\uD83C\uDFD3").queue();
            return;
        }
        // Send reply with name
        event.reply("\uD83D\uDC4B " + user.getAsMember().getAsMention()).queue();
    }

}
