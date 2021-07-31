package org.zerobzerot.discordbot;

import org.zerobzerot.discordbot.config.Config;

public class Main {

    public static void main(String[] args) {
        new Bot(Config.getInstance());
    }

}
