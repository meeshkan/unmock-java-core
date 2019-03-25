package io.unmock.core;

import io.unmock.core.persistence.Persistence;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TokenTest {

    final class TestPersistence implements Persistence {
        @Override
        public void saveHeaders(String hash, Map<String, String> headers) {

        }

        @Override
        public void saveBody(String hash, String body) {

        }

        @Override
        public void saveAuth(String auth) {

        }

        @Override
        public void saveToken(String token) {

        }

        @Override
        public void saveMetadata(String hash, PersistableData data) {

        }

        @Override
        public Map<String, String> loadHeaders(String hash) {
            return new HashMap();
        }

        @Override
        public String loadBody(String hash) {
            return null;
        }

        @Override
        public String loadAuth() {
            return null;
        }

        @Override
        public String loadToken() {
            return System.getenv("UNMOCK_TOKEN");
        }
    }

    @Test
    public void getsAccessToken() throws IOException {
        final String token = Token.getAccessToken(new TestPersistence(), System.getenv("UNMOCK_HOST"), Integer.parseInt(System.getenv("UNMOCK_PORT")));
        // smoke test, makes sure that token exists, should always be greater than 20
        Assert.assertTrue(token.length() > 20);
    }
}
