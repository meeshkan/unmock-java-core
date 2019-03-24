package io.unmock.core;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import io.unmock.core.logger.Logger;
import io.unmock.core.persistence.Persistence;

import java.util.Collection;
import java.util.Map;


final class UnmockInternalOptions {
    public final @NotNull Logger logger;
    public final @NotNull Persistence persistence;
    public final @Nullable String save;
    public final @NotNull String unmockHost;
    public final @NotNull String unmockPort;
    public final @NotNull boolean useInProduction;
    public final @Nullable String ignore;
    public final @Nullable String signature;
    public final @Nullable String token;
    public final @Nullable Collection<String> whitelist;

    public UnmockInternalOptions(
            Logger logger,
            Persistence persistence,
            String save,
            String unmockHost,
            String unmockPort,
            boolean useInProduction,
            String ignore,
            String signature,
            String token,
            Collection<String> whitelist) {
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
