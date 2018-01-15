package com.donkko.crypto.ticker;

import com.donkko.crypto.exchange.bithumb.api.response.TickerData;

public class Ticker {

    public Ticker(long timestamp, int currentPrice) {
        this.timestamp = timestamp;
        this.currentPrice = currentPrice;
    }

    public static Ticker of(TickerData bithumbData) {
        return new Ticker(bithumbData.getDate(), bithumbData.getClosingPrice());
    }

    private long timestamp;
    private int currentPrice;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(int currentPrice) {
        this.currentPrice = currentPrice;
    }
}
