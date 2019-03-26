package io.unmock.core;

import io.unmock.core.logger.SilentLogger;
import io.unmock.core.persistence.SilentPersistence;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import io.unmock.core.logger.Logger;
import io.unmock.core.persistence.Persistence;

import java.util.Collection;


public final class UnmockOptions {

    private final static String DEFAULT_UNMOCK_HOST = "api.unmock.io";
    private final static int DEFAULT_UNMOCK_PORT = 443;
    private final static boolean DEFAULT_USE_IN_PRODUCTION = false;

    public final @NotNull Logger logger;
    public final @NotNull Persistence persistence;
    public final @NotNull Save save;
    public final @NotNull String unmockHost;
    public final @NotNull int unmockPort;
    public final @NotNull boolean useInProduction;
    public final @Nullable String ignore;
    public final @Nullable String signature;
    public final @Nullable String token;
    public final @Nullable Collection<String> whitelist;

    static public class Builder {
        public @Nullable Logger logger = null;
        public @Nullable Persistence persistence = null;
        public @Nullable Save save = null;
        public @Nullable String unmockHost = null;
        public @Nullable Integer unmockPort = null;
        public @Nullable Boolean useInProduction = null;
        public @Nullable String ignore = null;
        public @Nullable String signature = null;
        public @Nullable String token = null;
        public @Nullable Collection<String> whitelist = null;

        public Builder() { }

        private Builder(Builder builder) {
            this.logger = builder.logger;
            this.persistence = builder.persistence;
            this.save = builder.save;
            this.unmockHost = builder.unmockHost;
            this.unmockPort = builder.unmockPort;
            this.useInProduction = builder.useInProduction;
            this.ignore = builder.ignore;
            this.signature = builder.signature;
            this.token = builder.token;
            this.whitelist = builder.whitelist;
        }

        public @NotNull Builder logger(@NotNull Logger logger) {
            Builder out = new Builder(this);
            out.logger = logger;
            return out;
        }

        public @NotNull Builder persistence(@NotNull Persistence persistence) {
            Builder out = new Builder(this);
            out.persistence = persistence;
            return out;
        }

        public @NotNull Builder save(@NotNull Save save) {
            Builder out = new Builder(this);
            out.save = save;
            return out;
        }

        public @NotNull Builder unmockHost(@NotNull String unmockHost) {
            Builder out = new Builder(this);
            out.unmockHost = unmockHost;
            return out;
        }

        public @NotNull Builder unmockPort(@NotNull Integer unmockPort) {
            Builder out = new Builder(this);
            out.unmockPort = unmockPort;
            return out;
        }

        public @NotNull Builder useInProduction(@NotNull Boolean useInProduction) {
            Builder out = new Builder(this);
            out.useInProduction = useInProduction;
            return out;
        }

        public @NotNull Builder ignore(@NotNull String ignore) {
            Builder out = new Builder(this);
            out.ignore = ignore;
            return out;
        }

        public @NotNull Builder signature(@NotNull String signature) {
            Builder out = new Builder(this);
            out.signature = signature;
            return out;
        }

        public @NotNull Builder token(@NotNull String token) {
            Builder out = new Builder(this);
            out.token = token;
            return out;
        }

        public @NotNull Builder whitelist(@NotNull Collection<String> whitelist) {
            Builder out = new Builder(this);
            out.whitelist = whitelist;
            return out;
        }

        public @NotNull UnmockOptions build() {
            return new UnmockOptions(
                    this.logger != null ? this.logger : new SilentLogger(),
                    this.persistence != null ? this.persistence : new SilentPersistence(),
                    this.save != null ? this.save : Save.ofBoolean(false),
                    this.unmockHost != null ? this.unmockHost : DEFAULT_UNMOCK_HOST,
                    this.unmockPort != null ? this.unmockPort : DEFAULT_UNMOCK_PORT,
                    this.useInProduction != null ? this.useInProduction : DEFAULT_USE_IN_PRODUCTION,
                    this.ignore,
                    this.signature,
                    this.token,
                    this.whitelist
            );
        }
    }

    private UnmockOptions(
            @NotNull Logger logger,
            @NotNull Persistence persistence,
            @NotNull Save save,
            @NotNull String unmockHost,
            @NotNull int unmockPort,
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