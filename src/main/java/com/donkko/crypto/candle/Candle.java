package com.donkko.crypto.candle;

import java.time.LocalDateTime;

public class Candle {

    private Candle(LocalDateTime datetime, int maxPrice, int minPrice, int currentPrice) {
        this.datetime = datetime;
        this.maxPrice = maxPrice;
        this.minPrice = minPrice;
        this.currentPrice = currentPrice;
    }

    public static Candle of(LocalDateTime datetime, int maxPrice, int minPrice, int currentPrice) {
        return new Candle(datetime, maxPrice, minPrice, currentPrice);
    }

    private LocalDateTime datetime;
    private int maxPrice;
    private int minPrice;
    private int currentPrice;

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public int getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(int currentPrice) {
        this.currentPrice = currentPrice;
    }
}
