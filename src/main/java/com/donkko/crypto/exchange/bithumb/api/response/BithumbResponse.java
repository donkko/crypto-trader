package com.donkko.crypto.exchange.bithumb.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Value;

@Value
public class BithumbResponse<T> {

    private String status;
    private String message;
    @JsonProperty("order_id") private String orderId;
    private T data;
}
