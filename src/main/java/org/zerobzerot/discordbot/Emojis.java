package org.zerobzerot.discordbot;

public enum Emojis {

    /**
     * 🤓
     */
    NERD("\uD83E\uDD13"),

    /**
     * 🤨
     */
    RAISED_EYEBROW("\uD83E\uDD28"),

    /**
     * 🥾
     */
    BOOT("\uD83E\uDD7E"),

    /**
     * 🏓
     */
    PING_PONG("\uD83C\uDFD3"),

    /**
     * 👋
     */
    WAVE("\uD83D\uDC4B"),

    /**
     * 💩
     */
    POOP("\uD83D\uDCA9"),

    ;

    private final String s;

    Emojis(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return s;
    }

}
