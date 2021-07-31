package org.zerobzerot.discordbot.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.zerobzerot.discordbot.TimeParser;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

class TimeParserTests {

    /**
     * 1 second diff allowed
     */
    private static void assertInstant(Instant instant, int value, ChronoUnit unit) {
        Assertions.assertNotNull(instant);
        Assertions.assertTrue(instant.isBefore(Instant.now().plus(value, unit).plus(1, ChronoUnit.SECONDS)));
        Assertions.assertTrue(instant.isAfter(Instant.now().plus(value, unit).minus(1, ChronoUnit.SECONDS)));
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
    }

    @Test
    void days() {
        assertInstant(TimeParser.parseInstant("1days"), 1, ChronoUnit.DAYS);
        assertInstant(TimeParser.parseInstant("1 days"), 1, ChronoUnit.DAYS);
        assertInstant(TimeParser.parseInstant("1day"), 1, ChronoUnit.DAYS);
        assertInstant(TimeParser.parseInstant("1 day"), 1, ChronoUnit.DAYS);
        assertInstant(TimeParser.parseInstant("1d"), 1, ChronoUnit.DAYS);
        assertInstant(TimeParser.parseInstant("1 d"), 1, ChronoUnit.DAYS);
    }

    @Test
    void weeks() {
        assertInstant(TimeParser.parseInstant("1weeks"), 7, ChronoUnit.DAYS);
        assertInstant(TimeParser.parseInstant("1 weeks"), 7, ChronoUnit.DAYS);
        assertInstant(TimeParser.parseInstant("1week"), 7, ChronoUnit.DAYS);
        assertInstant(TimeParser.parseInstant("1 week"), 7, ChronoUnit.DAYS);
        assertInstant(TimeParser.parseInstant("1w"), 7, ChronoUnit.DAYS);
        assertInstant(TimeParser.parseInstant("1 w"), 7, ChronoUnit.DAYS);
    }

    @Test
    void months() {
        assertInstant(TimeParser.parseInstant("1months"), 28, ChronoUnit.DAYS);
        assertInstant(TimeParser.parseInstant("1 months"), 28, ChronoUnit.DAYS);
        assertInstant(TimeParser.parseInstant("1month"), 28, ChronoUnit.DAYS);
        assertInstant(TimeParser.parseInstant("1 month"), 28, ChronoUnit.DAYS);
        assertInstant(TimeParser.parseInstant("1mon"), 28, ChronoUnit.DAYS);
        assertInstant(TimeParser.parseInstant("1 mon"), 28, ChronoUnit.DAYS);
        assertInstant(TimeParser.parseInstant("1mo"), 28, ChronoUnit.DAYS);
        assertInstant(TimeParser.parseInstant("1 mo"), 28, ChronoUnit.DAYS);
    }

    @Test
    void years() {
        assertInstant(TimeParser.parseInstant("1years"), 356, ChronoUnit.DAYS);
        assertInstant(TimeParser.parseInstant("1 years"), 356, ChronoUnit.DAYS);
        assertInstant(TimeParser.parseInstant("1year"), 356, ChronoUnit.DAYS);
        assertInstant(TimeParser.parseInstant("1 year"), 356, ChronoUnit.DAYS);
        assertInstant(TimeParser.parseInstant("1y"), 356, ChronoUnit.DAYS);
        assertInstant(TimeParser.parseInstant("1 y"), 356, ChronoUnit.DAYS);
    }

}
