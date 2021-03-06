package org.zerobzerot.discordbot;

public enum Emojis {

    /**
     * ๐ค
     */
    NERD("\uD83E\uDD13"),

    /**
     * ๐คจ
     */
    RAISED_EYEBROW("\uD83E\uDD28"),

    /**
     * ๐ฅพ
     */
    BOOT("\uD83E\uDD7E"),

    /**
     * ๐
     */
    PING_PONG("\uD83C\uDFD3"),

    /**
     * ๐
     */
    WAVE("\uD83D\uDC4B"),

    /**
     * ๐ฉ
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
