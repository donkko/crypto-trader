package com.donkko.crypto.timewindow;

import static com.donkko.crypto.constant.TradingConstants.MAX_NUMBER_OF_TIMEWINDOW;
import static com.donkko.crypto.constant.TradingConstants.TIMEWINDOW_MINUTES;
import static com.donkko.crypto.util.TimeUtils.getRepresentativeTimeWindowStamp;

import java.time.LocalDateTime;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

import org.springframework.stereotype.Component;

import com.donkko.crypto.candle.Candle;
import com.donkko.crypto.candle.CandleManager;
import com.donkko.crypto.timewindow.TimeWindow.Type;

@Component
public class TimeWindowManager {

    public TimeWindowManager(CandleManager candleManager) {
        this.candleManager = candleManager;
    }

    private final CandleManager candleManager;

    private final ConcurrentLinkedDeque<TimeWindow> timeWindows = new ConcurrentLinkedDeque<>();

    public Deque<TimeWindow> getTimeWindows() {
        return timeWindows;
    }

    public TimeWindow getCurrentTimeWindow() throws EmptyTimeWindowException, InvalidTimeWindowException {
        if (timeWindows.isEmpty()) {
            throw new EmptyTimeWindowException();
        }

        TimeWindow latestTimeWindow = timeWindows.peekLast();
        if (latestTimeWindow.getType() != Type.NORMAL) {
            throw new InvalidTimeWindowException("Not normal type timeWindow!!!!!");
        }
        return latestTimeWindow;
    }

    public TimeWindow getPreviousTimeWindow() throws EmptyTimeWindowException, InvalidTimeWindowException {
        if (timeWindows.isEmpty() || timeWindows.size() == 1) {
            throw new EmptyTimeWindowException();
        }

        // LinkedDeque 는 제일 끝의 요소밖에 꺼낼 수 없기 때문에 currentTimeWindow 를 한번 꺼냈다가 다시 집어넣는다
        TimeWindow currentTimeWindow = timeWindows.pollLast();
        TimeWindow previousTimeWindow = timeWindows.peekLast();
        timeWindows.addLast(currentTimeWindow);

        if (previousTimeWindow.getType() != Type.NORMAL) {
            throw new InvalidTimeWindowException("Not normal type timeWindow!!!!!");
        }
        return previousTimeWindow;
    }

    public void updateTimeWindows() {
        addTimeWindowIfTimePassed();
        updateCurrentTimeWindow();
        removeTimeWindow();
    }

    private void addTimeWindowIfTimePassed() {
        LocalDateTime currentTimeWindowStamp = getRepresentativeTimeWindowStamp(LocalDateTime.now());

        if (timeWindows.isEmpty()) {
            timeWindows.addLast(
                    TimeWindow.of(Type.ERROR, currentTimeWindowStamp, Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 0));
            return;
        }

        TimeWindow latestTimeWindow = timeWindows.getLast();

        if (latestTimeWindow.getDatetime().isEqual(currentTimeWindowStamp)) {
            return;
        }

        // 새로운 timeWindow 를 추가해야 할 상황이라면
        if (latestTimeWindow.getDatetime().isBefore(currentTimeWindowStamp)) {
            List<Candle> candles =
                    candleManager.getCandles(latestTimeWindow.getDatetime(), currentTimeWindowStamp);

            // API 에러 때문에 캔들 데이터가 쌓이지 않은 경우
            if (candles.isEmpty()) {
                latestTimeWindow.setType(Type.ERROR);
            } else {

                int maxPrice = candles.stream().map(Candle::getMaxPrice).max(Integer::compareTo).get();
                int minPrice = candles.stream().map(Candle::getMinPrice).min(Integer::compareTo).get();
                int priceRange = maxPrice - minPrice;
                int endPrice = candles.get(candles.size() - 1).getCurrentPrice();

                latestTimeWindow.setType(Type.NORMAL);
                latestTimeWindow.setMaxPrice(maxPrice);
                latestTimeWindow.setMinPrice(minPrice);
                latestTimeWindow.setPriceRange(priceRange);
                latestTimeWindow.setEndPrice(endPrice);
            }

            timeWindows.addLast(
                    TimeWindow.of(Type.ERROR, currentTimeWindowStamp, Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 0));
        }
    }

    private void updateCurrentTimeWindow() {
        TimeWindow latestTimeWindow = timeWindows.getLast();
        Candle latestCandle = candleManager.peekLastCandle();
        if (latestCandle == null) {
            return;
        }

        Integer currentPrice = latestCandle.getCurrentPrice();

        latestTimeWindow.setType(Type.NORMAL);
        if (latestTimeWindow.getMaxPrice() < currentPrice) latestTimeWindow.setMaxPrice(currentPrice);
        if (latestTimeWindow.getMinPrice() > currentPrice) latestTimeWindow.setMinPrice(currentPrice);
    }

    private void removeTimeWindow() {

        if (timeWindows.isEmpty()) {
            return;
        }

        LocalDateTime now = LocalDateTime.now();
        TimeWindow oldestTimeWindow = timeWindows.getFirst();

        if (oldestTimeWindow.getDatetime()
                            .isBefore(now.minusMinutes(TIMEWINDOW_MINUTES * MAX_NUMBER_OF_TIMEWINDOW))) {
            timeWindows.removeFirst();
        }
    }
}
