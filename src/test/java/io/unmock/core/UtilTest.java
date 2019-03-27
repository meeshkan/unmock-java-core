package io.unmock.core;

import com.google.gson.Gson;
import io.unmock.core.logger.SilentLogger;
import io.unmock.core.persistence.SilentPersistence;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.Assert;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class UtilTest {

    static private @NotNull Map<String, String> makeHeaders() {
        final Map<String, String> out = new HashMap<>();
        out.put("Authorization", "Foo");
        out.put("User-Agent", "Bar");
        return out;
    }

    @Test
    public void buildsCorrectPathWithIgnoreAndSignature() throws UnsupportedEncodingException {

        Assert.assertEquals("The correct path is built with ignore and a signature","/x/?story=%5B%5D&path=%2Fv1%2Fx&hostname=www.foo.com&method=GET&headers=%7B%22Authorization%22%3A%22Foo%22%2C%22User-Agent%22%3A%22Bar%22%7D&ignore=%22path%22&signature=my-signature", Util.buildPath(
                makeHeaders(),
                "www.foo.com",
                new Gson().toJson("path"),
                "GET",
                "/v1/x",
                "my-signature",
                new ArrayList<>(),
                "api.unmock.io",
                true)
        );
    }
    @Test
    public void buildsCorrectPathWithSignature() throws UnsupportedEncodingException {
        Assert.assertEquals("The correct path is built with a signature","/x/?story=%5B%5D&path=%2Fv1%2Fx&hostname=www.foo.com&method=GET&headers=%7B%22Authorization%22%3A%22Foo%22%2C%22User-Agent%22%3A%22Bar%22%7D&signature=my-signature", Util.buildPath(
                makeHeaders(),
                "www.foo.com",
                null,
                "GET",
                "/v1/x",
                "my-signature",
                new ArrayList<>(),
                "api.unmock.io",
                true)
        );
    }
    @Test
    public void buildsCorrectPath() throws UnsupportedEncodingException {
        Assert.assertEquals("The correct path is built without ignore or signature","/x/?story=%5B%5D&path=%2Fv1%2Fx&hostname=www.foo.com&method=GET&headers=%7B%22Authorization%22%3A%22Foo%22%2C%22User-Agent%22%3A%22Bar%22%7D", Util.buildPath(
                makeHeaders(),
                "www.foo.com",
                null,
                "GET",
                "/v1/x",
                null,
                new ArrayList<>(),
                "api.unmock.io",
                true)
        );
    }
    @Test
    public void endReporterSmokeTestWithNulls() {
        Util.endReporter(
                null,
                null,
                new HashMap<>(),
                null,
                new SilentLogger(),
                null,
                null,
                new SilentPersistence(),
                null,
                false,
                new ArrayList<>(),
                false,
                null);
    }
    @Test
    public void endReporterSmokeTestWithNoNulls() {
        Util.endReporter(
                "foo",
                "bar",
                new HashMap<>(),
                "foo.bac.io",
                new SilentLogger(),
                "GET",
                "/foo",
                new SilentPersistence(),
                Save.ofBoolean(false),
                false,
                new ArrayList<>(Arrays.asList("hello", "world")),
                false,
                new PersistableData(new HashMap<>(), "b", "c", "d"));
    }
}
