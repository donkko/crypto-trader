package com.donkko.crypto.exchange.korbit.api.response;

public class KorbitTicker {

    private Long timestamp;
    private Integer last;

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getLast() {
        return last;
    }

    public void setLast(Integer last) {
        this.last = last;
    }
}
