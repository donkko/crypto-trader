package com.donkko.crypto.schedule;

import javax.annotation.PostConstruct;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.donkko.crypto.candle.CandleManager;
import com.donkko.crypto.exchange.korbit.api.KorbitApiService;
import com.donkko.crypto.timewindow.TimeWindowManager;
import com.donkko.crypto.trader.Trader;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ScheduledTasks {

    private final KorbitApiService korbitApiService;
    private final CandleManager candleManager;
    private final TimeWindowManager timeWindowManager;
    private final Trader trader;

    @PostConstruct
    public void initToken() throws Exception {
        korbitApiService.issueAndRefreshAccessToken();
    }

    @Scheduled(cron = "0 0/20 * * * *")
    public void issueAndRefreshAccessToken() {
        try {
            System.out.println("$$$ issueAndRefreshAccessToken() $$$");
            korbitApiService.issueAndRefreshAccessToken();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Scheduled(cron = "0/1 * * * * *")
    public void addOrUpdateCandle() {
        try {
            candleManager.addOrUpdateCandle();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Scheduled(cron = "0/10 * * * * *")
    public void removeCandle() {
        candleManager.removeOldCandles();
    }

    @Scheduled(cron = "0/1 * * * * *")
    public void updateTimeWindow() {
        timeWindowManager.updateTimeWindows();
    }

    // 리팩해야함 일단은 하드코딩
    @Scheduled(cron = "0/1 2-58 * * * *")
    public void buy() {
        trader.buy();
    }

    // 리팩해야함 일단은 하드코딩
    @Scheduled(cron = "59 29,59 * * * *")
    public void sell() {
        trader.sell();
    }
}
