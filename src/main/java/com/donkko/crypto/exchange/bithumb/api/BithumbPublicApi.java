package com.donkko.crypto.exchange.bithumb.api;

import com.donkko.crypto.exchange.bithumb.api.response.BithumbResponse;
import com.donkko.crypto.exchange.bithumb.api.response.TickerData;
import com.donkko.crypto.model.Currency;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BithumbPublicApi {

    @GET("/public/ticker/{currency}")
    Call<BithumbResponse<TickerData>> getTickerData(@Path("currency") Currency currency);
}
