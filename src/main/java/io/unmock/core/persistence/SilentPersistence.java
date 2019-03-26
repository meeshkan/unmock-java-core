package io.unmock.core.persistence;

import io.unmock.core.PersistableData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class SilentPersistence implements Persistence {
    @Override
    public void saveHeaders(@NotNull String hash, @NotNull Map<String, String> headers) {

    }

    @Override
    public void saveBody(@NotNull String hash, @NotNull String body) {

    }

    @Override
    public void saveAuth(@NotNull String auth) {

    }

    @Override
    public void saveToken(@NotNull String token) {

    }

    @Override
    public void saveMetadata(@NotNull String hash, @NotNull PersistableData data) {

    }

    @Nullable
    @Override
    public Map<String, String> loadHeaders(@NotNull String hash) {
        return null;
    }

    @Nullable
    @Override
    public String loadBody(@NotNull String hash) {
        return null;
    }

    @Nullable
    @Override
    public String loadAuth() {
        return null;
    }

    @Nullable
    @Override
    public String loadToken() {
        return null;
    }
}
