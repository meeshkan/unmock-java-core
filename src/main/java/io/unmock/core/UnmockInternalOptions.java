package io.unmock.core;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import io.unmock.core.logger.Logger;
import io.unmock.core.persistence.Persistence;

import java.util.Collection;
import java.util.Map;


final class UnmockInternalOptions {
    public final @NotNull Logger logger;
    public final @NotNull Persistence persistence;
    public final @NotNull Save save;
    public final @NotNull String unmockHost;
    public final @NotNull String unmockPort;
    public final @NotNull boolean useInProduction;
    public final @Nullable String ignore;
    public final @Nullable String signature;
    public final @Nullable String token;
    public final @Nullable Collection<String> whitelist;

    public UnmockInternalOptions(
            @NotNull Logger logger,
            @NotNull Persistence persistence,
            @NotNull Save save,
            @NotNull String unmockHost,
            @NotNull String unmockPort,
            @NotNull boolean useInProduction,
            @Nullable String ignore,
            @Nullable String signature,
            @Nullable String token,
            @Nullable Collection<String> whitelist) {
        this.logger = logger;
        this.persistence = persistence;
        this.save = save;
        this.unmockHost = unmockHost;
        this.unmockPort = unmockPort;
        this.useInProduction = useInProduction;
        this.ignore = ignore;
        this.signature = signature;
        this.token = token;
        this.whitelist = whitelist;
    }
}
