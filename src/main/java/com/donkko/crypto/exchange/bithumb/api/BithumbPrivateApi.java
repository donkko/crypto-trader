package com.donkko.crypto.exchange.bithumb.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.donkko.crypto.exchange.bithumb.api.example.Api_Client;
import com.donkko.crypto.exchange.bithumb.api.response.BithumbResponse;
import com.donkko.crypto.model.Currency;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class BithumbPrivateApi {

    public BithumbPrivateApi(Api_Client apiClient) {
        this.apiClient = apiClient;
    }

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Api_Client apiClient;

    public BithumbResponse<Map<String, Double>> getMyBalance(Currency currency) throws IOException {
        HashMap<String, String> rgParams = new HashMap<>();
        rgParams.put("currency", currency.name());
        String result = apiClient.callApi("/info/balance", rgParams);
        return objectMapper.readValue(result, BithumbResponse.class);
    }

    public String placeMarketBuy(Currency currency, double unit) throws Exception {
        HashMap<String, String> rgParams = new HashMap<>();
        rgParams.put("currency", currency.name());
        rgParams.put("units", String.format("%.4f", Math.floor(unit * 10000) / 10000));
        return apiClient.callApi("/trade/market_buy", rgParams);
    }

    public String placeMarketSell(Currency currency, double unit) {
        HashMap<String, String> rgParams = new HashMap<>();
        rgParams.put("currency", currency.name());
        rgParams.put("units", String.format("%.4f", Math.floor(unit * 10000) / 10000));
        return apiClient.callApi("/trade/market_sell", rgParams);
    }
}
