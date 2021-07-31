package org.zerobzerot.discordbot.util;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class TimeParser {

    private static final Pattern pattern = Pattern.compile("(\\d+)\\s*(\\w+)");

    private static final Map<String, ChronoUnit> lookup = new HashMap<>();

    static {
        lookup.put("minutes", ChronoUnit.MINUTES);
        lookup.put("minute", ChronoUnit.MINUTES);
        lookup.put("min", ChronoUnit.MINUTES);
        lookup.put("mi", ChronoUnit.MINUTES);
        lookup.put("m", ChronoUnit.MINUTES);

        lookup.put("hours", ChronoUnit.HOURS);
        lookup.put("hour", ChronoUnit.HOURS);
        lookup.put("hr", ChronoUnit.HOURS);
        lookup.put("h", ChronoUnit.HOURS);

        lookup.put("days", ChronoUnit.DAYS);
        lookup.put("day", ChronoUnit.DAYS);
        lookup.put("d", ChronoUnit.DAYS);

        lookup.put("weeks", ChronoUnit.WEEKS);
        lookup.put("week", ChronoUnit.WEEKS);
        lookup.put("w", ChronoUnit.WEEKS);

        lookup.put("months", ChronoUnit.MONTHS);
        lookup.put("month", ChronoUnit.MONTHS);
        lookup.put("mon", ChronoUnit.MONTHS);
        lookup.put("mo", ChronoUnit.MONTHS);

        lookup.put("years", ChronoUnit.YEARS);
        lookup.put("year", ChronoUnit.YEARS);
        lookup.put("y", ChronoUnit.YEARS);
    }

    private TimeParser() {
    }

    public static Instant parseInstant(String raw) {
        Matcher m = pattern.matcher(raw);
        if (m.find()) {
            try {
                int value = Integer.parseInt(m.group(1));
                ChronoUnit unit = lookup.get(m.group(2).toLowerCase());
                if (unit == ChronoUnit.WEEKS) {
                    value = value * 7;
                    unit = ChronoUnit.DAYS;
                } else if (unit == ChronoUnit.MONTHS) {
                    value = value * 30;
                    unit = ChronoUnit.DAYS;
                } else if (unit == ChronoUnit.YEARS) {
                    value = value * 356;
                    unit = ChronoUnit.DAYS;
                }
                return Instant.now().plus(value, unit);
            } catch (IndexOutOfBoundsException | NumberFormatException ignored) {
            }
        }
        return null;
    }

}
