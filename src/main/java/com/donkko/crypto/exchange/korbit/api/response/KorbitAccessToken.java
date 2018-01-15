package com.donkko.crypto.exchange.korbit.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KorbitAccessToken {

    @JsonProperty("token_type") private String tokenType;
    @JsonProperty("access_token") private String accessToken;
    @JsonProperty("expires_in") private int expiresIn;
    @JsonProperty("refresh_token") private String refreshToken;

    public String getBearerWithAccessToken() {
        return "Bearer " + accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
