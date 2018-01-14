package com.donkko.crypto.ticker;

import org.springframework.stereotype.Service;

import com.donkko.crypto.exchange.bithumb.api.BithumbPublicApi;
import com.donkko.crypto.exchange.bithumb.api.response.BithumbResponse;
import com.donkko.crypto.exchange.bithumb.api.response.TickerData;
import com.donkko.crypto.model.Currency;

import lombok.RequiredArgsConstructor;
import retrofit2.Response;

//@Service
@RequiredArgsConstructor
public class TickerServiceBithumb implements TickerService {

    private final BithumbPublicApi bithumbPublicApi;

    @Override
    public Ticker getCurrentTicker() throws Exception {
        Response<BithumbResponse<TickerData>> response = bithumbPublicApi.getTickerData(Currency.ETH).execute();
        if (response.isSuccessful()) {
            BithumbResponse<TickerData> body = response.body();
            if (!body.getStatus().equals("0000")) {
                throw new ExchangeApiException(body.getMessage());
            }
            return Ticker.of(body.getData());
        }
        throw new ExchangeApiException(response.toString());
    }
}
