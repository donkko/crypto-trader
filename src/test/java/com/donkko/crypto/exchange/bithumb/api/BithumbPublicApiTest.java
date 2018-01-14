package com.donkko.crypto.exchange.bithumb.api;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.donkko.crypto.exchange.bithumb.api.BithumbPublicApi;
import com.donkko.crypto.exchange.bithumb.api.BithumbApiConfig;
import com.donkko.crypto.exchange.bithumb.api.response.BithumbResponse;
import com.donkko.crypto.exchange.bithumb.api.response.TickerData;
import com.donkko.crypto.model.Currency;

import retrofit2.Call;
import retrofit2.Response;

public class BithumbPublicApiTest {

    @Test
    public void getTicketData() throws Exception {
        BithumbPublicApi bithumbPublicApi = new BithumbApiConfig().bithumbPublicApi();
        Call<BithumbResponse<TickerData>> call = bithumbPublicApi.getTickerData(Currency.BTC);
        Response<BithumbResponse<TickerData>> response = call.execute();

        assertTrue(response.isSuccessful());
        assertNotNull(response.body());
    }
}
