package org.zerobzerot.discordbot.commands;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.exceptions.HierarchyException;
import net.dv8tion.jda.api.exceptions.InsufficientPermissionException;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.requests.restaction.AuditableRestAction;
import org.tinylog.Logger;
import org.zerobzerot.discordbot.Emojis;

public class KickCommand extends SlashCommand {

    public KickCommand() {
        super("kick", "kick someone", Type.MOD);
        addOption(OptionType.USER, "user", "user to kick", true);
        addOption(OptionType.STRING, "reason", "reason for kick", false);
    }

    @Override
    public void run(SlashCommandEvent event) {
        var userOption = event.getOption("user");
        // Check if user option is provided and user is member in server
        if (userOption == null || userOption.getAsMember() == null) {
            // Send warning reply
            event.reply("User not found " + Emojis.RAISED_EYEBROW).setEphemeral(false).queue();
            return;
        }
        // Set reason if option is provided
        var message = "Kicked by an operator. - The client has been disconnected using the /kick command.";
        final var reason = event.getOption("reason");
        if (reason != null) message = reason.getAsString();
        // Send notification
        // TODO: clear user message history
        // Kick user
        final AuditableRestAction<Void> action;
        try {
            action = userOption.getAsMember().kick(message);
            event.reply(Emojis.BOOT + " Kicked " + userOption.getAsMember().getAsMention() + " with message: " + "\"" + message + "\"").setEphemeral(true).queue();
        } catch (InsufficientPermissionException | HierarchyException | IllegalArgumentException ex) {
            Logger.warn(ex.getMessage());
            event.reply("Unable to kick " + userOption.getAsMember().getAsMention() + " " + Emojis.RAISED_EYEBROW).setEphemeral(false).queue();
            return;
        }
        action.queue();
    }

}
