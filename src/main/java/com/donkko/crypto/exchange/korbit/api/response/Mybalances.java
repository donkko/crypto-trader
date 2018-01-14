package com.donkko.crypto.exchange.korbit.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Mybalances {

    @Data
    public class Balance {
        private Double available;
        @JsonProperty("trade_in_use") private Double tradeInUse;
        @JsonProperty("withdrawal_in_use") private Double withdrawalInUse;
    }

    private Balance krw;
    private Balance btc;
    private Balance eth;
    private Balance etc;
    private Balance xrp;
}
