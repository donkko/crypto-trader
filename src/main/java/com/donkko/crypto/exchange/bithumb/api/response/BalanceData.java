package com.donkko.crypto.exchange.bithumb.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BalanceData {

    public BalanceData(double availableKrw, double availableEth) {
        this.availableKrw = availableKrw;
        this.availableEth = availableEth;
    }

    @JsonProperty("available_krw") private double availableKrw;
    @JsonProperty("available_eth") private double availableEth;

    public double getAvailableKrw() {
        return availableKrw;
    }

    public double getAvailableEth() {
        return availableEth;
    }
}
