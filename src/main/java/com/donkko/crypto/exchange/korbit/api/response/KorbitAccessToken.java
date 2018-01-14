package com.donkko.crypto.exchange.korbit.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class KorbitAccessToken {

    @JsonProperty("token_type") private String tokenType;
    @JsonProperty("access_token") private String accessToken;
    @JsonProperty("expires_in") private int expiresIn;
    @JsonProperty("refresh_token") private String refreshToken;

    public String getBearerWithAccessToken() {
        return "Bearer " + accessToken;
    }
}
