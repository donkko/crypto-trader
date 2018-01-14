package com.donkko.crypto.candle;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class Candle {

    private LocalDateTime datetime;
    private int maxPrice;
    private int minPrice;
    private int currentPrice;
}
