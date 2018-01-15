package com.donkko.crypto.ticker;

import org.springframework.stereotype.Service;

import com.donkko.crypto.exchange.korbit.api.KorbitApiService;
import com.donkko.crypto.exchange.korbit.api.response.KorbitTicker;

@Service
public class TickerServiceKorbit implements TickerService {

    public TickerServiceKorbit(KorbitApiService korbitApiService) {
        this.korbitApiService = korbitApiService;
    }

    private final KorbitApiService korbitApiService;

    @Override
    public Ticker getCurrentTicker() throws Exception {
        KorbitTicker korbitTicker = korbitApiService.getTicker();
        return new Ticker(korbitTicker.getTimestamp(), korbitTicker.getLast());
    }
}
