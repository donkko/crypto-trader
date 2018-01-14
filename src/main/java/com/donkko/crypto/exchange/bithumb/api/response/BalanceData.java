package com.donkko.crypto.exchange.bithumb.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Value;

@Value
public class BalanceData {

    @JsonProperty("available_krw") private double availableKrw;
    @JsonProperty("available_eth") private double availableEth;
}
