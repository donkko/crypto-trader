package com.donkko.crypto.trader;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.donkko.crypto.candle.CandleManager;
import com.donkko.crypto.exchange.bithumb.api.BithumbPrivateApi;
import com.donkko.crypto.exchange.bithumb.api.response.BithumbResponse;
import com.donkko.crypto.model.Currency;
import com.donkko.crypto.timewindow.EmptyTimeWindowException;
import com.donkko.crypto.timewindow.TimeWindow;
import com.donkko.crypto.timewindow.TimeWindowManager;

import lombok.RequiredArgsConstructor;

//@Component
@RequiredArgsConstructor
public class BithumbTrader implements Trader {

    private final CandleManager candleManager;
    private final TimeWindowManager timeWindowManager;
    private final BithumbPrivateApi bithumbPrivateApi;

    @Override
    public void buy() {
        //System.out.println("#### trader.buy()");
        try {

            double currentPrice = Double.valueOf(candleManager.peekLastCandle().getCurrentPrice());

            BithumbResponse<Map<String, Double>> balance = bithumbPrivateApi.getMyBalance(Currency.ETH);
            double krwBalance = Double.valueOf(String.valueOf(balance.getData().get("available_krw")));
            double unitToBuy = Double.valueOf(krwBalance / 10 * 8) / currentPrice;

            TimeWindow previousTimeWindow;
            TimeWindow currentTimeWindow;
            try {
                previousTimeWindow = timeWindowManager.getPreviousTimeWindow();
                currentTimeWindow = timeWindowManager.getCurrentTimeWindow();
            } catch (EmptyTimeWindowException ex) {
                return;
            }

            if (currentTimeWindow.getMinPrice() + previousTimeWindow.getPriceRange() / 2 < currentPrice) {
                System.out.println(String.format("#### prevMinPrice: %d, prevPriceRange/2: %d currentPrice: %f"
                        ,previousTimeWindow.getMinPrice(), previousTimeWindow.getPriceRange() / 2, currentPrice));
                bithumbPrivateApi.placeMarketBuy(Currency.ETH, unitToBuy);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
    }

    @Override
    public void sell() {
        System.out.println("#### trader.sell()");
        try {
            BithumbResponse<Map<String, Double>> balance = bithumbPrivateApi.getMyBalance(Currency.ETH);
            double ethBalance = Double.valueOf(String.valueOf(balance.getData().get("available_eth")));

            if (ethBalance > 0) {
                System.out.println("#### Selling!!!");
                bithumbPrivateApi.placeMarketSell(Currency.ETH, ethBalance);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
    }
}
