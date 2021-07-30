package org.zerobzerot.discordbot.commands;

import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;

public class PingCommand extends SlashCommand {

    public PingCommand() {
        super("ping", "play ping pong", Type.PUBLIC);
        addOption(OptionType.USER, "user", "who to play with", false);
    }

    @Override
    public void run(SlashCommandEvent event) {
        OptionMapping user = event.getOption("user");

        // Check if user present
        if (user == null) {
            // Send reply without mention
            sendReply(event, new MessageBuilder().append("\uD83C\uDFD3").build());
            return;
        }

        // Check if user is member in server
        final Member member = user.getAsMember();
        if (member == null) {
            // This should never happen because the discord client restricts input to existing users only
            // but this can probably be bypassed when sending with custom clients, so we should account for it.
            sendReply(event, new MessageBuilder()
                .append("I have never seen ")
                .append(String.valueOf(user.getAsMentionable()))
                .append(" on here...")
                .build());
            return;
        }

        // Send reply with mention
        sendReply(event, new MessageBuilder()
            .append("\uD83D\uDC4B ")
            .append(user.getAsMember().getAsMention())
            .build());

    }

}
