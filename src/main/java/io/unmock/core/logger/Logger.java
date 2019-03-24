package io.unmock.core.logger;

import com.sun.istack.internal.NotNull;

public interface Logger {
    void log(@NotNull String message);
}
