package com.donkko.crypto.exchange.korbit.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Mybalances {

    public class Balance {
        private Double available;
        @JsonProperty("trade_in_use") private Double tradeInUse;
        @JsonProperty("withdrawal_in_use") private Double withdrawalInUse;

        public Double getAvailable() {
            return available;
        }

        public void setAvailable(Double available) {
            this.available = available;
        }

        public Double getTradeInUse() {
            return tradeInUse;
        }

        public void setTradeInUse(Double tradeInUse) {
            this.tradeInUse = tradeInUse;
        }

        public Double getWithdrawalInUse() {
            return withdrawalInUse;
        }

        public void setWithdrawalInUse(Double withdrawalInUse) {
            this.withdrawalInUse = withdrawalInUse;
        }
    }

    private Balance krw;
    private Balance btc;
    private Balance eth;
    private Balance etc;
    private Balance xrp;

    public Balance getKrw() {
        return krw;
    }

    public void setKrw(Balance krw) {
        this.krw = krw;
    }

    public Balance getBtc() {
        return btc;
    }

    public void setBtc(Balance btc) {
        this.btc = btc;
    }

    public Balance getEth() {
        return eth;
    }

    public void setEth(Balance eth) {
        this.eth = eth;
    }

    public Balance getEtc() {
        return etc;
    }

    public void setEtc(Balance etc) {
        this.etc = etc;
    }

    public Balance getXrp() {
        return xrp;
    }

    public void setXrp(Balance xrp) {
        this.xrp = xrp;
    }
}
