package io.unmock.core;

import com.google.gson.Gson;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import io.unmock.core.logger.Logger;
import io.unmock.core.persistence.Persistence;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public final class Util {

    static public final String UNMOCK_UA_HEADER_NAME = "X-Unmock-Client-User-Agent";

    static public final @NotNull boolean hostIsWhitelisted(
            @Nullable  Collection<String> whitelist,
            @Nullable String host) {
        return whitelist != null && host != null && whitelist.contains(host);
    }

    static public final @NotNull String buildPath(
            @NotNull Map<String, String> headerz,
            @Nullable String host,
            @Nullable String ignore,
            @Nullable String method,
            @Nullable String path,
            @Nullable String signature,
            List<String> story,
            String unmockHost,
            boolean xy
    ) throws UnsupportedEncodingException {
        return host != null && host.equals(unmockHost) ?
                path :
                Arrays.stream(new String[] {
                        "/",
                        xy ? "x" : "y",
                        "/?story=",
                        URLEncoder.encode(story.toString(), "UTF-8"),
                        "&path=",
                        URLEncoder.encode(path != null ? path : "", "UTF-8"),
                        "&hostname=",
                        URLEncoder.encode(host != null ? host : "", "UTF-8"),
                        "&method=",
                        URLEncoder.encode(method != null ? method : "", "UTF-8"),
                        "&headers=",
                        URLEncoder.encode(new Gson().toJson(headerz), "UTF-8"),
                        ignore != null ? "&ignore=" + URLEncoder.encode(ignore, "UTF-8") : "",
                        signature != null ? "&signature=" + URLEncoder.encode(signature, "UTF-8") : "",
                }).collect(Collectors.joining(""));
    }

    static public final void endReporter(
            @Nullable String body,
            @Nullable String data,
            @NotNull  Map<String, String> headers,
            @Nullable String host,
            @NotNull Logger logger,
            @Nullable String method,
            @Nullable String path,
            @NotNull Persistence persistence,
            @Nullable Save save,
            @NotNull boolean selfCall,
            @NotNull List<String> story,
            @NotNull boolean xy,
            @Nullable PersistableData persistableData
    ) {
        if (!selfCall) {
            final String hash = headers.containsKey("unmock-hash") ? headers.get("unmock-hash") : null;
            // in case the end function has been called multiple times
            // we skip invoking it again
            if (!story.contains(hash)) {
                story.add(0, hash);
                logger.log("*****url-called*****");
                logger.log(Arrays.stream(new String[] {
                        "Hi! We see you've called ",
                        method,
                        host,
                        path,
                        data != null ? "with data " + data : ""
                }).collect(Collectors.joining("")));
                logger.log(Arrays.stream(new String[] {
                        "We've sent you mock data back. You can edit your mock at https://unmock.io/",
                        xy ? "x" : "y",
                        "/",
                        hash,
                        "."
                }).collect(Collectors.joining("")));
                if (save != null && ((save.getKind() == Save.Kind.BOOLEAN && save.bool()) || (save.getKind() == Save.Kind.COLLECTION && save.collection().contains(hash)))) {
                    persistence.saveHeaders(hash, headers);
                    if (persistableData != null) {
                        persistence.saveMetadata(hash, persistableData);
                    }
                    if (body != null) {
                        persistence.saveBody(hash, body);
                    }
                }
            }
        }
    }
}
