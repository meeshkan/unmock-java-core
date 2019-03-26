package io.unmock.core.persistence;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import io.unmock.core.PersistableData;

import java.util.Map;

public interface Persistence {
    void saveHeaders(@NotNull String hash, @NotNull Map<String, String> headers);
    void saveBody(@NotNull String hash, @NotNull String body);
    void saveAuth(@NotNull String auth);
    void saveToken(@NotNull String token);
    void saveMetadata(@NotNull String hash, @NotNull PersistableData data);
    @Nullable Map<String, String> loadHeaders(@NotNull String hash);
    @Nullable String loadBody(@NotNull String hash);
    @Nullable String loadAuth();
    @Nullable String loadToken();
}
