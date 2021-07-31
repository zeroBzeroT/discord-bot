package org.zerobzerot.discordbot.commands;

import net.dv8tion.jda.api.entities.MessageHistory;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import org.tinylog.Logger;

public class ClearCommand extends SlashCommand {

    public ClearCommand() {
        super("clear", "clear message history", Type.MOD);
        addOption(OptionType.INTEGER, "amount", "number of messages to clear", true);
    }

    @Override
    public void run(SlashCommandEvent event) {
        OptionMapping option = event.getOption("amount");
        // Check if option is present
        if (option == null) {
            // Send warning reply
            event.reply("Required option not specified \uD83E\uDD28").queue();
            return;
        }
        var amount = (int) option.getAsLong();
        if (amount > 100) amount = 100;
        // Clear history
        event.reply("Clearing last " + amount + " messages...").queue();
        final MessageHistory.MessageRetrieveAction action;
        try {
            action = event.getChannel().getHistoryBefore(event.getId(), amount);
        } catch (IllegalArgumentException ex) {
            Logger.warn(ex.getMessage());
            return;
        }
        action.queue(h -> h.getRetrievedHistory().forEach(message -> message.delete().queue()));
    }

}
