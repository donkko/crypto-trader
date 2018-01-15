package com.donkko.crypto.exchange.bithumb.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TickerData {

    public TickerData(double openingPrice, int closingPrice, int minPrice, int maxPrice, double averagePrice,
                      double unitsTraded, double volume1day, double volume7day, int buyPrice, int sellPrice,
                      long date) {
        this.openingPrice = openingPrice;
        this.closingPrice = closingPrice;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.averagePrice = averagePrice;
        this.unitsTraded = unitsTraded;
        this.volume1day = volume1day;
        this.volume7day = volume7day;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.date = date;
    }

    @JsonProperty("opening_price") private double openingPrice;
    @JsonProperty("closing_price") private int closingPrice;
    @JsonProperty("min_price") private int minPrice;
    @JsonProperty("max_price") private int maxPrice;
    @JsonProperty("average_price") private double averagePrice;
    @JsonProperty("units_traded") private double unitsTraded;
    @JsonProperty("volume_1day") private double volume1day;
    @JsonProperty("volume_7day") private double volume7day;
    @JsonProperty("buy_price") private int buyPrice;
    @JsonProperty("sell_price") private int sellPrice;
    private long date;

    public double getOpeningPrice() {
        return openingPrice;
    }

    public void setOpeningPrice(double openingPrice) {
        this.openingPrice = openingPrice;
    }

    public int getClosingPrice() {
        return closingPrice;
    }

    public void setClosingPrice(int closingPrice) {
        this.closingPrice = closingPrice;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public double getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(double averagePrice) {
        this.averagePrice = averagePrice;
    }

    public double getUnitsTraded() {
        return unitsTraded;
    }

    public void setUnitsTraded(double unitsTraded) {
        this.unitsTraded = unitsTraded;
    }

    public double getVolume1day() {
        return volume1day;
    }

    public void setVolume1day(double volume1day) {
        this.volume1day = volume1day;
    }

    public double getVolume7day() {
        return volume7day;
    }

    public void setVolume7day(double volume7day) {
        this.volume7day = volume7day;
    }

    public int getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(int buyPrice) {
        this.buyPrice = buyPrice;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(int sellPrice) {
        this.sellPrice = sellPrice;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
