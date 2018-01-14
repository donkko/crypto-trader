package com.donkko.crypto.exchange.korbit.api.response;

import lombok.Value;

@Value
public class KorbitTicker {
    private final Long timestamp;
    private final Integer last;
}
