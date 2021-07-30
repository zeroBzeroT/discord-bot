package org.zerobzerot.discordbot.commands;

import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.exceptions.HierarchyException;
import net.dv8tion.jda.api.exceptions.InsufficientPermissionException;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.requests.restaction.AuditableRestAction;
import net.dv8tion.jda.api.requests.restaction.interactions.ReplyAction;
import org.tinylog.Logger;

public class KickCommand extends SlashCommand {

    public KickCommand() {
        super("kick", "kick someone");
        addOption(OptionType.USER, "user", "who to kick", true);
        addOption(OptionType.STRING, "reason", "reason for kick", false);
    }

    @Override
    public void run(SlashCommandEvent event) {
        final OptionMapping user = event.getOption("user");
        final OptionMapping reason = event.getOption("reason");

        // Check if user present
        if (user == null) {
            sendReply(event, new MessageBuilder().append("I have no idea who you are talking about...").build());
            return;
        }
        // Check if user is in server present
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

        // Set reason if provided
        String message = "Kicked by an operator. - The client has been disconnected using the /kick command.";
        if (reason != null) message = reason.getAsString();

        // Kick user
        sendReply(event, new MessageBuilder()
            .append("\uD83E\uDD7E ")
            .append(user.getAsMember().getAsMention())
            .build());
        final AuditableRestAction<Void> action;
        try {
            action = member.kick(message);
        } catch (InsufficientPermissionException | HierarchyException | IllegalArgumentException ex) {
            Logger.warn(ex.getMessage());
            return;
        }
        action.queue();
    }

    private void sendReply(SlashCommandEvent event, Message reply) {
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
