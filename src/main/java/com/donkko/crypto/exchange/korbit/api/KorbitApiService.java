package com.donkko.crypto.exchange.korbit.api;

import org.springframework.stereotype.Service;

import com.donkko.crypto.exchange.korbit.api.response.KorbitAccessToken;
import com.donkko.crypto.exchange.korbit.api.response.KorbitTicker;
import com.donkko.crypto.exchange.korbit.api.response.Mybalances;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KorbitApiService {

    private static final String WHICH_CURRENCY = "eth_krw";

    private final KorbitApi korbitApi;
    private final KorbitApiSetting korbitApiSetting;

    public void issueAndRefreshAccessToken() throws Exception {
        korbitApiSetting.setAccessToken(issueAccessToken());
        korbitApiSetting.setAccessToken(refreshAccessToken());
    }

    KorbitAccessToken issueAccessToken() throws Exception {
        return korbitApi.issueAccessToken(
                korbitApiSetting.getClientId(),
                korbitApiSetting.getClientSecret(),
                korbitApiSetting.getUsername(),
                korbitApiSetting.getPassword(),
                "password").execute().body();
    }

    KorbitAccessToken refreshAccessToken() throws Exception {
        return korbitApi.refreshAccessToken(
                korbitApiSetting.getClientId(),
                korbitApiSetting.getClientSecret(),
                korbitApiSetting.getAccessToken().getRefreshToken(),
                "refresh_token").execute().body();
    }

    public KorbitTicker getTicker() throws Exception {
        return korbitApi.getTicker(WHICH_CURRENCY).execute().body();
    }

    public Mybalances getMyBalances() throws Exception {
        return korbitApi.getMyBalances(korbitApiSetting.getAccessToken().getBearerWithAccessToken()).execute().body();
    }

    public void placeMarketBuy(Integer fiatAmount) throws Exception {
        fiatAmount = fiatAmount / 1000 * 1000;
        if (fiatAmount < 5000) {
            throw new Exception("#### eth_krw fiat_amount must be greater than or equal to 5000");
        }
        korbitApi.placeMarketBuy(korbitApiSetting.getAccessToken().getBearerWithAccessToken(),
                                 WHICH_CURRENCY, "market", fiatAmount,
                                 System.currentTimeMillis()).execute();
    }

    public void placeMarketSell(Double coinAmount) throws Exception {
        korbitApi.placeMarketSell(korbitApiSetting.getAccessToken().getBearerWithAccessToken(),
                                  WHICH_CURRENCY, "market", coinAmount,
                                  System.currentTimeMillis()).execute();
    }
}
