package com.donkko.crypto.candle;

import static com.donkko.crypto.constant.TradingConstants.MAX_NUMBER_OF_TIMEWINDOW;
import static com.donkko.crypto.constant.TradingConstants.TIMEWINDOW_MINUTES;
import static com.donkko.crypto.util.TimeUtils.getLocalDateTimeWithoutSeconds;

import java.time.LocalDateTime;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.donkko.crypto.ticker.Ticker;
import com.donkko.crypto.ticker.TickerService;

@Component
public class CandleManager {

    public CandleManager(TickerService tickerService) {
        this.tickerService = tickerService;
    }

    private final TickerService tickerService;
    private final ConcurrentLinkedDeque<Candle> candles = new ConcurrentLinkedDeque<>();

    public Deque<Candle> getCandles() {
        return candles;
    }

    public List<Candle> getCandles(LocalDateTime from, LocalDateTime to) {
        return candles.stream()
                      .filter(candle -> candle.getDatetime().isAfter(from) && candle.getDatetime().isBefore(to))
                      .collect(Collectors.toList());
    }

    public Candle peekLastCandle() {
        return candles.peekLast();
    }

    public void addOrUpdateCandle() {

        Ticker ticker;

        try {
            ticker = tickerService.getCurrentTicker();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        LocalDateTime tickerDateTime = getLocalDateTimeWithoutSeconds(ticker.getTimestamp());
        int currentPrice = ticker.getCurrentPrice();

        Candle newestCandle = candles.peekLast();

        if (candles.isEmpty() || newestCandle.getDatetime().isBefore(tickerDateTime)) {
            candles.addLast(Candle.of(tickerDateTime, currentPrice, currentPrice, currentPrice));
        }

        else {
            if (newestCandle.getMaxPrice() < currentPrice) {
                newestCandle.setMaxPrice(currentPrice);
            }
            if (newestCandle.getMinPrice() > currentPrice) {
                newestCandle.setMinPrice(currentPrice);
            }
            newestCandle.setCurrentPrice(currentPrice);
        }
    }

    public void removeOldCandles() {

        LocalDateTime now = LocalDateTime.now();
        Candle oldestCandle = candles.peekFirst();

        if (oldestCandle == null) {
            return;
        }

        if (oldestCandle.getDatetime()
                        .isBefore(now.minusMinutes(TIMEWINDOW_MINUTES * MAX_NUMBER_OF_TIMEWINDOW))) {
            candles.removeFirst();
        }
    }
}

