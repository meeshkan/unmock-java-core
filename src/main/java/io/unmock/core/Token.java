package io.unmock.core;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import io.unmock.core.persistence.Persistence;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Map;

public class Token {

    static private boolean PINGABLE = false;

    static void makeHeader(@NotNull HttpURLConnection connection, @NotNull String token) {
        connection.setRequestProperty("Authorization", "Bearer " + token);
    }

    static @NotNull boolean canPingWithAccessToken(@NotNull String accessToken, @NotNull String unmockHost, @NotNull int unmockPort) {
        HttpURLConnection connection = null;
        Integer response = null;
        try {
            final URL url = new URL("https", unmockHost, unmockPort, "/ping");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            makeHeader(connection, accessToken);
            connection.setUseCaches(false);
            response = connection.getResponseCode();
        } catch (IOException e) {
            // report?
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return response == HttpURLConnection.HTTP_OK;
    }

    static @NotNull String exchangeRefreshTokenForAccessToken(@NotNull String refreshToken, @NotNull String unmockHost, @NotNull int unmockPort) throws IOException {
        HttpURLConnection connection = null;
        String token = null;
        final String urlParameters = "{\"refreshToken\":\"" + refreshToken + "\"}";
        try {
            final URL url = new URL("https", unmockHost, unmockPort, "/ping");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setRequestProperty("Content-Type", "application/json");

            connection.setRequestProperty("Content-Length",
                    Integer.toString(urlParameters.getBytes().length));

            connection.setUseCaches(false);
            connection.setDoOutput(true);

            //Send request
            DataOutputStream wr = new DataOutputStream (
                    connection.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.close();

            //Get Response
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuffer response = new StringBuffer();
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\n');
            }
            rd.close();
            Type type = new TypeToken<Map<String, String>>(){}.getType();
            Map<String, String> myMap = new Gson().fromJson(response.toString(), type);
            token = myMap.get("accessToken");
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return token;
    }

    static @Nullable String getAccessToken(@NotNull Persistence persistence, @NotNull String unmockHost, @NotNull int unmockPort) throws IOException {
        String accessToken = persistence.loadAuth();
        if (accessToken != null) {
            if (!PINGABLE) {
                PINGABLE = canPingWithAccessToken(accessToken, unmockHost, unmockPort);
                if (!PINGABLE) {
                    accessToken = null;
                }
            }
        }
        if (accessToken == null) {
            String refreshToken = persistence.loadToken();
            if (refreshToken != null) {
                accessToken = exchangeRefreshTokenForAccessToken(refreshToken, unmockHost, unmockPort);
                if (accessToken != null) {
                    persistence.saveAuth(accessToken);
                } else {
                    throw new IOException("Incorrect server response: did not get accessToken");
                }
            } else {
                // if there is no refresh token, we default to the "y" version of the service
                return null;
            }
        }
        if (!PINGABLE && accessToken != null) {
            PINGABLE = canPingWithAccessToken(accessToken, unmockHost, unmockPort);
            if (!PINGABLE) {
                throw new IOException("Internal authorization error");
            }
        }
        return accessToken;
    }
}
