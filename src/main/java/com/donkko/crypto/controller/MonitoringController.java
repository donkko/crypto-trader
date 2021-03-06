package com.donkko.crypto.controller;

import java.util.Deque;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.donkko.crypto.candle.Candle;
import com.donkko.crypto.candle.CandleManager;
import com.donkko.crypto.timewindow.TimeWindow;
import com.donkko.crypto.timewindow.TimeWindowManager;

@RestController
public class MonitoringController {

    public MonitoringController(CandleManager candleManager,
                                TimeWindowManager timeWindowManager) {
        this.candleManager = candleManager;
        this.timeWindowManager = timeWindowManager;
    }

    private final CandleManager candleManager;
    private final TimeWindowManager timeWindowManager;

    @GetMapping("/monitor/candles")
    public Deque<Candle> monitorCandles() {
        return candleManager.getCandles();
    }

    @GetMapping("/monitor/timewindows")
    public Deque<TimeWindow> monitorTimeWindows() {
        return timeWindowManager.getTimeWindows();
    }
}
