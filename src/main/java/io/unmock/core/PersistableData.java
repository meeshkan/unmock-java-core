package io.unmock.core;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

public final class PersistableData {
    public final @NotNull Map<String, String> requestHeaders;
    public final @NotNull String requestHost;
    public final @NotNull String requestMethod;
    public final @NotNull String requestPath;

    public PersistableData(
            Map<String, String> requestHeaders,
            String requestHost,
            String requestMethod,
            String requestPath) {
        this.requestHeaders = requestHeaders;
        this.requestHost = requestHost;
        this.requestMethod = requestMethod;
        this.requestPath = requestPath;
    }
}
