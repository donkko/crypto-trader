package com.donkko.crypto.ticker;

import com.donkko.crypto.exchange.bithumb.api.response.TickerData;

import lombok.Value;

@Value
public class Ticker {

    private long timestamp;
    private int currentPrice;

    public static Ticker of(TickerData bithumbData) {
        return new Ticker(bithumbData.getDate(), bithumbData.getClosingPrice());
    }
}
