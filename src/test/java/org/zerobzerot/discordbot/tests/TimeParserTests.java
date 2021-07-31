package org.zerobzerot.discordbot.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.zerobzerot.discordbot.util.TimeParser;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

class TimeParserTests {

    /**
     * 10 milliseconds diff allowed
     */
    private static void assertInstant(Instant instant, int value, ChronoUnit unit) {
        Assertions.assertNotNull(instant);
        final var deadline = Instant.now().plus(value, unit);
        Assertions.assertTrue(instant.isBefore(deadline.plus(10, ChronoUnit.MILLIS)));
        Assertions.assertTrue(instant.isAfter(deadline.minus(10, ChronoUnit.MILLIS)));
    }

    @Test
    void minutes() {
        assertInstant(TimeParser.parseInstant("1minutes"), 1, ChronoUnit.MINUTES);
        assertInstant(TimeParser.parseInstant("1 minutes"), 1, ChronoUnit.MINUTES);
        assertInstant(TimeParser.parseInstant("1minute"), 1, ChronoUnit.MINUTES);
        assertInstant(TimeParser.parseInstant("1 minute"), 1, ChronoUnit.MINUTES);
        assertInstant(TimeParser.parseInstant("1min"), 1, ChronoUnit.MINUTES);
        assertInstant(TimeParser.parseInstant("1 min"), 1, ChronoUnit.MINUTES);
        assertInstant(TimeParser.parseInstant("1mi"), 1, ChronoUnit.MINUTES);
        assertInstant(TimeParser.parseInstant("1 mi"), 1, ChronoUnit.MINUTES);
        assertInstant(TimeParser.parseInstant("1m"), 1, ChronoUnit.MINUTES);
        assertInstant(TimeParser.parseInstant("1 m"), 1, ChronoUnit.MINUTES);
        assertInstant(TimeParser.parseInstant("245 Minutes"), 245, ChronoUnit.MINUTES);
    }

    @Test
    void hours() {
        assertInstant(TimeParser.parseInstant("1hours"), 1, ChronoUnit.HOURS);
        assertInstant(TimeParser.parseInstant("1 hours"), 1, ChronoUnit.HOURS);
        assertInstant(TimeParser.parseInstant("1hour"), 1, ChronoUnit.HOURS);
        assertInstant(TimeParser.parseInstant("1 hour"), 1, ChronoUnit.HOURS);
        assertInstant(TimeParser.parseInstant("1hr"), 1, ChronoUnit.HOURS);
        assertInstant(TimeParser.parseInstant("1 hr"), 1, ChronoUnit.HOURS);
        assertInstant(TimeParser.parseInstant("1h"), 1, ChronoUnit.HOURS);
        assertInstant(TimeParser.parseInstant("1 h"), 1, ChronoUnit.HOURS);
        assertInstant(TimeParser.parseInstant("25 Hours"), 25, ChronoUnit.HOURS);
    }

    @Test
    void days() {
        assertInstant(TimeParser.parseInstant("1days"), 1, ChronoUnit.DAYS);
        assertInstant(TimeParser.parseInstant("1 days"), 1, ChronoUnit.DAYS);
        assertInstant(TimeParser.parseInstant("1day"), 1, ChronoUnit.DAYS);
        assertInstant(TimeParser.parseInstant("1 day"), 1, ChronoUnit.DAYS);
        assertInstant(TimeParser.parseInstant("1d"), 1, ChronoUnit.DAYS);
        assertInstant(TimeParser.parseInstant("1 d"), 1, ChronoUnit.DAYS);
        assertInstant(TimeParser.parseInstant("100 Day"), 100, ChronoUnit.DAYS);
    }

    @Test
    void weeks() {
        assertInstant(TimeParser.parseInstant("1weeks"), 7, ChronoUnit.DAYS);
        assertInstant(TimeParser.parseInstant("1 weeks"), 7, ChronoUnit.DAYS);
        assertInstant(TimeParser.parseInstant("1week"), 7, ChronoUnit.DAYS);
        assertInstant(TimeParser.parseInstant("1 week"), 7, ChronoUnit.DAYS);
        assertInstant(TimeParser.parseInstant("1w"), 7, ChronoUnit.DAYS);
        assertInstant(TimeParser.parseInstant("1 w"), 7, ChronoUnit.DAYS);
        assertInstant(TimeParser.parseInstant("20 Weeks"), 7 * 20, ChronoUnit.DAYS);
    }

    @Test
    void months() {
        assertInstant(TimeParser.parseInstant("1months"), 30, ChronoUnit.DAYS);
        assertInstant(TimeParser.parseInstant("1 months"), 30, ChronoUnit.DAYS);
        assertInstant(TimeParser.parseInstant("1month"), 30, ChronoUnit.DAYS);
        assertInstant(TimeParser.parseInstant("1 month"), 30, ChronoUnit.DAYS);
        assertInstant(TimeParser.parseInstant("1mon"), 30, ChronoUnit.DAYS);
        assertInstant(TimeParser.parseInstant("1 mon"), 30, ChronoUnit.DAYS);
        assertInstant(TimeParser.parseInstant("1mo"), 30, ChronoUnit.DAYS);
        assertInstant(TimeParser.parseInstant("1 mo"), 30, ChronoUnit.DAYS);
        assertInstant(TimeParser.parseInstant("1000000 mo"), 30 * 1000000, ChronoUnit.DAYS);
    }

    @Test
    void years() {
        assertInstant(TimeParser.parseInstant("1years"), 356, ChronoUnit.DAYS);
        assertInstant(TimeParser.parseInstant("1 years"), 356, ChronoUnit.DAYS);
        assertInstant(TimeParser.parseInstant("1year"), 356, ChronoUnit.DAYS);
        assertInstant(TimeParser.parseInstant("1 year"), 356, ChronoUnit.DAYS);
        assertInstant(TimeParser.parseInstant("1y"), 356, ChronoUnit.DAYS);
        assertInstant(TimeParser.parseInstant("1 y"), 356, ChronoUnit.DAYS);
        assertInstant(TimeParser.parseInstant("30 y"), 356 * 30, ChronoUnit.DAYS);
    }

}
