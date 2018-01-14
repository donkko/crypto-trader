package com.donkko.crypto.timewindow;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class TimeWindow {

    private Type type;
    private final LocalDateTime datetime;
    private int maxPrice;
    private int minPrice;
    private int priceRange;
    private int endPrice;

    public enum Type {
        NORMAL, ERROR;
    }
}
