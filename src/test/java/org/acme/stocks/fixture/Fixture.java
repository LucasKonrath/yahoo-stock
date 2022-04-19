package org.acme.stocks.fixture;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jeasy.random.EasyRandom;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Fixture {
    private static final EasyRandom GENERATOR = new EasyRandom();

    public static <T> T make(final Class<T> fixtureType) {
        return GENERATOR.nextObject(fixtureType);
    }
}
