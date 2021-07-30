package org.zerobzerot.discordbot;

import java.nio.file.Path;

public class Main {

    public static void main(String[] args) {
        new Bot(Config.load(Path.of("config.yml")));
    }

}
