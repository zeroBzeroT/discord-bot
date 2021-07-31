package org.zerobzerot.discordbot.commands;

import cc.neckbeard.utils.UuidConverter;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;

import java.util.UUID;

public class LinkCommand extends SlashCommand {

    public LinkCommand() {
        super("link", "link your mc account", Type.PUBLIC);
        addOption(OptionType.STRING, "uuid", "mc account uuid", true);
        addOption(OptionType.INTEGER, "pin", "secret pin", true);
    }

    @Override
    public void run(SlashCommandEvent event) {
        var uuidOption = event.getOption("uuid");
        var pinOption = event.getOption("pin");
        // Check if option is present
        if (uuidOption == null || pinOption == null) {
            // Send warning reply
            event.reply("Required option not specified \uD83E\uDD28").setEphemeral(false).queue();
            return;
        }
        final UUID uuid;
        try {
            uuid = UuidConverter.of(uuidOption.getAsString());
        } catch (IllegalArgumentException ex) {
            event.reply("That was not a valid uuid \uD83E\uDD28").setEphemeral(false).queue();
        }
        var pin = (int) pinOption.getAsLong();
        if (pin < 1 || pin > 9999) {
            event.reply("That was not a valid pin \uD83E\uDD28").setEphemeral(false).queue();
        }
        // TODO: load oldfaggotry data from redis, if uuid found and pin is correct, set role.
    }

}
