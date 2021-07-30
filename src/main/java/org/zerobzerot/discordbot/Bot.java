package org.zerobzerot.discordbot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.tinylog.Logger;

import javax.security.auth.login.LoginException;

public class Bot {

    public Bot(Config config) {
        final JDA jda;

        // build jda
        try {
            jda = JDABuilder
                .createDefault(config.getToken())
                // the things that are commented out are the things we do not disable!
                .disableCache( // https://ci.dv8tion.net/job/JDA/javadoc/net/dv8tion/jda/api/utils/cache/CacheFlag.html
                    CacheFlag.ACTIVITY,                     // getActivities()
                    CacheFlag.CLIENT_STATUS,                // getOnlineStatus()
                    CacheFlag.EMOTE,                        // getEmoteCache()
                    CacheFlag.MEMBER_OVERRIDES,             // getMemberPermissionOverrides()
                    CacheFlag.VOICE_STATE                   // getVoiceState()
                ).disableIntents( // https://ci.dv8tion.net/job/JDA/javadoc/net/dv8tion/jda/api/requests/GatewayIntent.html
                    GatewayIntent.GUILD_MEMBERS,            // This is a privileged gateway intent that is used to update user information and join/leaves (including kicks). This is required to cache all members of a guild (including chunking)
                    GatewayIntent.GUILD_BANS,               // This will only track guild bans and unbans
                    GatewayIntent.GUILD_EMOJIS,             // This will only track guild emote create/modify/delete. Most bots don't need this since they just use the emote id anyway.
                    GatewayIntent.GUILD_INVITES,            // This will only track invite create/delete. Most bots don't make use of invites since they are added through OAuth2 authorization by administrators.
                    GatewayIntent.GUILD_VOICE_STATES,       // Required to properly get information of members in voice channels and cache them. You cannot connect to a voice channel without this intent.
                    GatewayIntent.GUILD_PRESENCES,          // This is a privileged gateway intent this is only used to track activity and online-status of a user.
                    // GatewayIntent.GUILD_MESSAGES,        // This is used to receive incoming messages in guilds (servers), most bots will need this for commands.
                    GatewayIntent.GUILD_MESSAGE_REACTIONS,  // This is used to track reactions on messages in guilds (servers). Can be useful to make a paginated embed or reaction role management.
                    GatewayIntent.GUILD_MESSAGE_TYPING,     // This is used to track when a user starts typing in guilds (servers). Almost no bot will have a use for this.
                    // GatewayIntent.DIRECT_MESSAGES,       // This is used to receive incoming messages in private channels (DMs). You can still send private messages without this intent.
                    GatewayIntent.DIRECT_MESSAGE_REACTIONS, // This is used to track reactions on messages in private channels (DMs).
                    GatewayIntent.DIRECT_MESSAGE_TYPING     // This is used to track when a user starts typing in private channels (DMs). Almost no bot will have a use for this.
                ).build();
        } catch (LoginException e) {
            Logger.error("JDA login failed: " + e.getMessage());
            System.exit(1);
            return;
        }

        // await ready
        try {
            jda.awaitReady();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Logger.error("JDA init failed: " + e.getMessage());
            System.exit(1);
            return;
        }

        // initialize commands
        jda.addEventListener(new CommandProcessor(jda));

        // set presence
        jda.getPresence().setPresence(OnlineStatus.ONLINE, Activity.competing("\uD83E\uDD13️"));

        Logger.info(jda.getSelfUser().getName() + " (" + jda.getSelfUser().getApplicationId() + ") is ready!");
    }

}
