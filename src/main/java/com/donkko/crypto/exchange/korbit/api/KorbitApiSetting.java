package com.donkko.crypto.exchange.korbit.api;

import com.donkko.crypto.exchange.korbit.api.response.KorbitAccessToken;

public class KorbitApiSetting {

    public KorbitApiSetting(String clientId, String clientSecret, String username, String password) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.username = username;
        this.password = password;
    }

    private final String clientId;
    private final String clientSecret;
    private final String username;
    private final String password;
    private KorbitAccessToken accessToken;

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public KorbitAccessToken getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(KorbitAccessToken accessToken) {
        this.accessToken = accessToken;
    }
}
