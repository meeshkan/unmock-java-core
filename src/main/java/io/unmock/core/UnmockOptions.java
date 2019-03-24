package io.unmock.core;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import io.unmock.core.logger.Logger;
import io.unmock.core.persistence.Persistence;

import java.util.Collection;


final class UnmockOptions {
    public final @Nullable Logger logger;
    public final @Nullable Persistence persistence;
    public final @Nullable Save save;
    public final @Nullable String unmockHost;
    public final @Nullable String unmockPort;
    public final @Nullable boolean useInProduction;
    public final @Nullable String ignore;
    public final @Nullable String signature;
    public final @Nullable String token;
    public final @Nullable Collection<String> whitelist;

    public UnmockOptions(
            @Nullable Logger logger,
            @Nullable Persistence persistence,
            @Nullable Save save,
            @Nullable String unmockHost,
            @Nullable String unmockPort,
            @Nullable boolean useInProduction,
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
