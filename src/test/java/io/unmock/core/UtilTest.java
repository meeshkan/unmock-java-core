package io.unmock.core;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class UtilTest {

    @Test
    public void buildsCorrectPathWithIgnoreAndSignature() throws UnsupportedEncodingException {
        Assert.assertEquals("/x/?story=%5B%5D&path=%2Fv1%2Fx&hostname=www.foo.com&method=GET&headers=%7B%22Authorization%22%3A%22Foo%22%2C%22User-Agent%22%3A%22Bar%22%7D&ignore=%22path%22&signature=my-signature", Util.buildPath(
                Stream.of(new String[][] {
                        { "Authorization", "Foo" },
                        { "User-Agent", "Bar" },
                }).collect(Collectors.toMap(data -> data[0], data -> data[1])),
                "www.foo.com",
                null,
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
        Assert.assertEquals("/x/?story=%5B%5D&path=%2Fv1%2Fx&hostname=www.foo.com&method=GET&headers=%7B%22Authorization%22%3A%22Foo%22%2C%22User-Agent%22%3A%22Bar%22%7D&signature=my-signature", Util.buildPath(
                Stream.of(new String[][] {
                        { "Authorization", "Foo" },
                        { "User-Agent", "Bar" },
                }).collect(Collectors.toMap(data -> data[0], data -> data[1])),
                "www.foo.com",
                null,
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
        Assert.assertEquals("/x/?story=%5B%5D&path=%2Fv1%2Fx&hostname=www.foo.com&method=GET&headers=%7B%22Authorization%22%3A%22Foo%22%2C%22User-Agent%22%3A%22Bar%22%7D", Util.buildPath(
                Stream.of(new String[][] {
                        { "Authorization", "Foo" },
                        { "User-Agent", "Bar" },
                }).collect(Collectors.toMap(data -> data[0], data -> data[1])),
                "www.foo.com",
                null,
                null,
                "GET",
                "/v1/x",
                null,
                new ArrayList<>(),
                "api.unmock.io",
                true)
        );
    }
}
