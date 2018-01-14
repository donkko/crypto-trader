package com.donkko.crypto.exchange.korbit.api;

import org.springframework.stereotype.Component;

import com.donkko.crypto.exchange.korbit.api.response.KorbitAccessToken;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class KorbitApiSetting {

    private final String clientId;
    private final String clientSecret;
    private final String username;
    private final String password;
    private KorbitAccessToken accessToken;
}
