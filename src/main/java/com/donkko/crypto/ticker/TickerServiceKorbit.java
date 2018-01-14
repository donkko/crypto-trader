package com.donkko.crypto.ticker;

import org.springframework.stereotype.Service;

import com.donkko.crypto.exchange.korbit.api.KorbitApiService;
import com.donkko.crypto.exchange.korbit.api.response.KorbitTicker;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TickerServiceKorbit implements TickerService {

    private final KorbitApiService korbitApiService;

    @Override
    public Ticker getCurrentTicker() throws Exception {
        KorbitTicker korbitTicker = korbitApiService.getTicker();
        return new Ticker(korbitTicker.getTimestamp(), korbitTicker.getLast());
    }
}
