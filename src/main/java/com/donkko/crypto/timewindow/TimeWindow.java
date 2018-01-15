package com.donkko.crypto.timewindow;

import java.time.LocalDateTime;

public class TimeWindow {

    private TimeWindow(Type type, LocalDateTime datetime, int maxPrice, int minPrice, int priceRange,
                      int endPrice) {
        this.type = type;
        this.datetime = datetime;
        this.maxPrice = maxPrice;
        this.minPrice = minPrice;
        this.priceRange = priceRange;
        this.endPrice = endPrice;
    }

    public static TimeWindow of(Type type, LocalDateTime datetime, int maxPrice, int minPrice, int priceRange,
                         int endPrice) {
        return new TimeWindow(type, datetime, maxPrice, minPrice, priceRange, endPrice);
    }

    private Type type;
    private final LocalDateTime datetime;
    private int maxPrice;
    private int minPrice;
    private int priceRange;
    private int endPrice;

    public enum Type {
        NORMAL, ERROR;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public LocalDateTime getDatetime() {
        return datetime;
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

    public int getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(int priceRange) {
        this.priceRange = priceRange;
    }

    public int getEndPrice() {
        return endPrice;
    }

    public void setEndPrice(int endPrice) {
        this.endPrice = endPrice;
    }
}
