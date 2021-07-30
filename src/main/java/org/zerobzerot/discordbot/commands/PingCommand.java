package org.zerobzerot.discordbot.commands;

import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.exceptions.InsufficientPermissionException;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.requests.restaction.interactions.ReplyAction;
import org.tinylog.Logger;

public class PingCommand extends SlashCommand {

    public PingCommand() {
        super("ping", "play ping pong");
        addOption(OptionType.USER, "user", "who to play with", false);
    }

    @Override
    public void run(SlashCommandEvent event) {
        final Message reply;
        OptionMapping user = event.getOption("user");
        if (user != null) {
            Member member = user.getAsMember();
            if (member != null) {
                reply = new MessageBuilder()
                    .append("\uD83D\uDC4B ")
                    .append(user.getAsMember().getAsMention())
                    .build();
            } else {
                reply = new MessageBuilder()
                    .append("I have never seen ")
                    .append(String.valueOf(user.getAsMentionable()))
                    .append(" on here...")
                    .build();
            }
        } else reply = new MessageBuilder().append("\uD83C\uDFD3").build();

        final ReplyAction action;
        try {
            action = event.reply(reply);
        } catch (InsufficientPermissionException | UnsupportedOperationException | IllegalArgumentException ex) {
            Logger.warn(ex.getMessage());
            return;
        }
        action.queue();
    }

}
