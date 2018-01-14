package com.donkko.crypto.trader;

import org.springframework.stereotype.Component;

import com.donkko.crypto.candle.Candle;
import com.donkko.crypto.candle.CandleManager;
import com.donkko.crypto.exchange.korbit.api.KorbitApiService;
import com.donkko.crypto.exchange.korbit.api.response.Mybalances;
import com.donkko.crypto.timewindow.EmptyTimeWindowException;
import com.donkko.crypto.timewindow.TimeWindow;
import com.donkko.crypto.timewindow.TimeWindowManager;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class KorbitTrader implements Trader {

    private final CandleManager candleManager;
    private final TimeWindowManager timeWindowManager;
    private final KorbitApiService korbitApiService;

    @Override
    public void buy() {
        //System.out.println("#### trader.buy()");
        try {

            Candle lastCandle = candleManager.peekLastCandle();
            if (lastCandle == null) {
                return;
            }
            double currentPrice = Double.valueOf(lastCandle.getCurrentPrice());

            Mybalances mybalances = korbitApiService.getMyBalances();
            Double krwBalance = mybalances.getKrw().getAvailable();

            if (krwBalance < 10000) {
                return;
            }

            TimeWindow previousTimeWindow;
            TimeWindow currentTimeWindow;
            try {
                previousTimeWindow = timeWindowManager.getPreviousTimeWindow();
                currentTimeWindow = timeWindowManager.getCurrentTimeWindow();
            } catch (EmptyTimeWindowException ex) {
                return;
            }

            if (previousTimeWindow.getEndPrice() + previousTimeWindow.getPriceRange() / 2 < currentPrice) {
                System.out.println(String.format("#### prevEndPrice: %d, prevPriceRange/2: %d currentPrice: %f"
                        ,previousTimeWindow.getEndPrice(), previousTimeWindow.getPriceRange() / 2, currentPrice));
                korbitApiService.placeMarketBuy(krwBalance.intValue());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sell() {
        System.out.println("#### trader.sell()");
        try {
            Double ethBalance = korbitApiService.getMyBalances().getEth().getAvailable();

            if (ethBalance > 0) {
                System.out.println("#### Selling!!!");
                korbitApiService.placeMarketSell(ethBalance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
