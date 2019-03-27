package io.unmock.core;

import io.unmock.core.logger.Logger;
import io.unmock.core.logger.SilentLogger;
import io.unmock.core.persistence.Persistence;
import io.unmock.core.persistence.SilentPersistence;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

public class UnmockOptionsTest {

    @Test
    public void builderTest() {
        Persistence persistence = new SilentPersistence();
        Logger logger = new SilentLogger();
        Save save = Save.ofBoolean(true);
        Collection<String> whitelist = new ArrayList<>();
        UnmockOptions unmockOptions = new UnmockOptions.Builder()
                .unmockHost("foo.bar.com")
                .unmockPort(111)
                .save(save)
                .ignore("path")
                .logger(logger)
                .persistence(persistence)
                .useInProduction(true)
                .signature("hello-world")
                .token("goodbye-world")
                .whitelist(whitelist)
                .build();
        Assert.assertEquals("Unmock options has the correct host", "foo.bar.com", unmockOptions.unmockHost);
        Assert.assertEquals("Unmock options has the correct port", 111, unmockOptions.unmockPort);
        Assert.assertEquals("Unmock options has the correct ignore", "path", unmockOptions.ignore);
        Assert.assertEquals("Unmock options has the correct logger", logger, unmockOptions.logger);
        Assert.assertEquals("Unmock options has the correct persistence", persistence, unmockOptions.persistence);
        Assert.assertEquals("Unmock options has the correct use in production", true, unmockOptions.useInProduction);
        Assert.assertEquals("Unmock options has the correct signature", "hello-world", unmockOptions.signature);
        Assert.assertEquals("Unmock options has the correct token", "goodbye-world", unmockOptions.token);
        Assert.assertEquals("Unmock options has the correct whitelist", whitelist, unmockOptions.whitelist);
    }

    @Test
    public void builderTestWithDefaults() {
        UnmockOptions unmockOptions = new UnmockOptions.Builder().build();
        Assert.assertEquals("Unmock options has the correct host", "api.unmock.io", unmockOptions.unmockHost);
        Assert.assertEquals("Unmock options has the correct port", 443, unmockOptions.unmockPort);
        Assert.assertEquals("Unmock options has the correct ignore", null, unmockOptions.ignore);
        Assert.assertTrue("Unmock options has the correct logger", unmockOptions.logger instanceof Logger);
        Assert.assertTrue("Unmock options has the correct persistence", unmockOptions.persistence instanceof Persistence);
        Assert.assertEquals("Unmock options has the correct use in production", false, unmockOptions.useInProduction);
        Assert.assertEquals("Unmock options has the correct signature", null, unmockOptions.signature);
        Assert.assertEquals("Unmock options has the correct token", null, unmockOptions.token);
        Assert.assertEquals("Unmock options has the correct whitelist", null, unmockOptions.whitelist);
    }
}
