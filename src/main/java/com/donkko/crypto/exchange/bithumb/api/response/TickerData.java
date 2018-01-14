package com.donkko.crypto.exchange.bithumb.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Value;

@Value
public class TickerData {

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
}
